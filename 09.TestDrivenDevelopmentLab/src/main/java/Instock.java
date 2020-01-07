import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private Map<String, Product> products;

    public Instock() {
        this.products = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        this.products.put(product.getLabel(), product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (!this.products.containsKey(product)) {
            throw new IllegalArgumentException();
        }

        this.products.get(product).setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return this.products.values().stream()
                .skip(index)
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public Product findByLabel(String label) {
        if (!this.products.containsKey(label)) {
            throw new IllegalArgumentException();
        }
        return this.products.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        List<Product> list = new ArrayList<>();

        if (!(count > this.products.size())) {
            list = this.products.values().stream()
                    .sorted(Comparator.comparing(Product::getLabel))
                    .limit(count)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.products.values().stream()
                .filter(p -> p.getPrice() >= lo && p.getPrice() <= hi)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products.values().stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.products.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.products.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.products.values().stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.values().iterator();
    }
}
