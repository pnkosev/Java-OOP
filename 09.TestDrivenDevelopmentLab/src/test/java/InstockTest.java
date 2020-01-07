import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class InstockTest {
    private static final Product PRODUCT = new Product("Apple", 2.99, 1000);
    private static final String[] LABELS = {
            "B",
            "D",
            "C",
            "A",
            "E"
    };

    private Instock instock;

    @Before
    public void setup() {
        this.instock = new Instock();
    }

    @Test
    public void shouldReturnCorrectCountOnGetCount() {
        int count = this.instock.getCount();

        assertEquals(0, count);
    }

    @Test
    public void shouldIncreaseCountIfAddedCorrectly() {
        this.instock.add(PRODUCT);

        assertEquals(1, this.instock.getCount());
    }

    @Test
    public void shouldReturnTrueIfContainsProduct() {
        this.instock.add(PRODUCT);

        assertTrue(this.instock.contains(PRODUCT));
    }

    @Test
    public void shouldReturnFalseIfDoesntContainProduct() {
        this.instock.add(PRODUCT);

        assertFalse(this.instock.contains(new Product("Oranges", 1.99, 1000)));
    }

    @Test
    public void shouldFindByLabelIfPresent() {
        this.instock.add(PRODUCT);
        Product product = this.instock.findByLabel("Apple");

        assertEquals(PRODUCT, product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfAbsentOnFindByLabel() {
        this.instock.add(PRODUCT);
        this.instock.findByLabel("Orange");
    }

    @Test
    public void shouldChangeQuantityForPresentProduct() {
        this.instock.add(PRODUCT);
        int expectedQuantity = 2000;
        this.instock.changeQuantity("Apple", expectedQuantity);

        int actualQuantity = this.instock.findByLabel("Apple").getQuantity();

        assertEquals(expectedQuantity, actualQuantity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnChangeQuantityForAbsentProduct() {
        this.instock.add(PRODUCT);

        int expectedQuantity = 2000;
        this.instock.changeQuantity("Orange", expectedQuantity);
    }

    @Test
    public void shouldReturnNProductOnFindByIndexIfSuch() {
        fillStockWithProducts();

        int index = LABELS.length / 2;

        Product product = this.instock.find(index);

        assertEquals(LABELS[index], product.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowOnFindByIndexIfOutOfBounds() {
        fillStockWithProducts();

        int index = LABELS.length;

        this.instock.find(index);
    }

    @Test
    public void shouldReturnAllOnFindAllByQuantityIfQuantityPresent() {
        fillStockWithProducts();
        this.instock.add(PRODUCT);

        Iterable<Product> allByQuantity = this.instock.findAllByQuantity(100);

        int[] count = {0};
        allByQuantity.forEach(p -> count[0]++);

        assertEquals(1, count[0]);
    }

    @Test
    public void shouldReturnEmptyIterableOnFindAllByQuantityIfQuantityAbsent() {
        fillStockWithProducts();

        Iterable<Product> allByQuantity = this.instock.findAllByQuantity(200);

        int[] count = {0};
        allByQuantity.forEach(p -> count[0]++);

        assertEquals(0, count[0]);
    }

    @Test
    public void shouldFindProductsWithGivenPrice() {
        fillStockWithProducts();

        Iterable<Product> allByPrice = this.instock.findAllByPrice(2.99);

        final int[] count = {0};

        allByPrice.forEach(p -> {
            count[0] += 1;
        });

        assertEquals(1, count[0]);
    }

    @Test
    public void shouldReturnEmptyIterableIfCannotFindByPrice() {
        fillStockWithProducts();

        Iterable<Product> allByPrice = this.instock.findAllByPrice(1.99);

        final int[] count = {0};

        allByPrice.forEach(p -> {
            count[0] += 1;
        });

        assertEquals(0, count[0]);
    }

    @Test
    public void shouldReturnCorrectAmountOfProductsOnFindFirstMostExpensiveProductsIfEqualOrLessThanNPresent() {
        fillStockWithProducts();

        int count = 3;
        Iterable<Product> firstMostExpensiveProducts = this.instock.findFirstMostExpensiveProducts(count);

        int[] actualCount = {0};

        firstMostExpensiveProducts.forEach(p -> actualCount[0]++);

        assertEquals(count, actualCount[0]);
    }

    @Test
    public void shouldReturnCorrectProductsOnFindFirstMostExpensiveProductsIfEqualOrLessThanNPresent() {
        fillStockWithProducts();

        int count = 3;
        Iterable<Product> firstMostExpensiveProducts = this.instock.findFirstMostExpensiveProducts(count);

        int counter = LABELS.length - 1;
        for (Product firstMostExpensiveProduct : firstMostExpensiveProducts) {
            assertEquals(LABELS[counter--], firstMostExpensiveProduct.getLabel());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowOnFindFirstMostExpensiveProductsIfMoreThanNPresent() {
        fillStockWithProducts();
        this.instock.findFirstMostExpensiveProducts(LABELS.length + 1);
    }

    @Test
    public void shouldReturnAllItemsOnGetIterable() {
        fillStockWithProducts();

        Iterator<Product> allProducts = this.instock.iterator();

        int index = 0;
        while (allProducts.hasNext()) {
            assertEquals(LABELS[index++], allProducts.next().getLabel());
        }
    }

    @Test
    public void shouldReturnAllProductsInPriceRange() {
        fillStockWithProducts();
        Iterable<Product> allInRange = this.instock.findAllInRange(2.99, 3.99);

        int[] count = {0};
        allInRange.forEach(p -> count[0]++);

        assertEquals(2, count[0]);
    }

    @Test
    public void shouldReturnCorrectCountOfProductsOnFindOnAlphabeticalOrderIfLessThanNPresent() {
        fillStockWithProducts();

        int count = LABELS.length / 2;
        Iterable<Product> firstByAlphabeticalOrder = this.instock.findFirstByAlphabeticalOrder(count);

        String[] testArr = LABELS.clone();
        Arrays.sort(testArr);

        int counter = 0;
        for (Product p: firstByAlphabeticalOrder) {
            assertEquals(testArr[counter++], p.getLabel());
        }
    }

    @Test
    public void shouldReturnEmptyIterableOnFindOnAlphabeticalOrderIfMoreNPresent() {
        fillStockWithProducts();

        int count = LABELS.length + 1;
        Iterable<Product> firstByAlphabeticalOrder = this.instock.findFirstByAlphabeticalOrder(count);

        int counter = 0;
        for (Product p: firstByAlphabeticalOrder) {
            counter++;
        }

        assertEquals(0, counter);
    }

    private void fillStockWithProducts() {
        for (int i = 0; i < LABELS.length; i++) {
            this.instock.add(new Product(LABELS[i], 2.99 + i, i + 100));
        }
    }
}