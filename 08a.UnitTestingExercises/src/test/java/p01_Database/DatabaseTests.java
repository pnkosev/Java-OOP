package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTests {
    private Integer[] inBoundArray;
    private Database database;

    @Before
    public void setup() throws OperationNotSupportedException {
        this.inBoundArray = new Integer[]{1, 2, 3, 4, 5};
        this.database = new Database(this.inBoundArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfArgsLengthGreaterThanSixteenIntegers() throws OperationNotSupportedException {
        Integer[] arr = new Integer[17];

        new Database(arr);
    }

    @Test
    public void shouldStoreArrayWithLengthSixteen() {
        Integer[] actual = this.database.getElements();

        Assert.assertArrayEquals(actual, this.inBoundArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfAddingNullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void shouldAddElementAtTheEndOfStack() throws OperationNotSupportedException {
        Integer expected = 6;
        this.database.add(expected);

        Integer[] actual = this.database.getElements();
        Integer lastElement = actual[actual.length - 1];

        Assert.assertEquals(expected, lastElement);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenEndIsReached() throws OperationNotSupportedException {
        Integer[] arr = new Integer[16];

        Database database = new Database(arr);

        database.add(1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenRemovingFromEmptyArray() throws OperationNotSupportedException {
        Database database = new Database();
        database.remove();
    }

    @Test
    public void shouldRemoveLastElementUponRemove() throws OperationNotSupportedException {
        Integer[] actualArr = this.database.getElements();

        Integer[] expectedArr = new Integer[actualArr.length - 1];

        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = actualArr[i];
        }

        this.database.remove();

        actualArr = this.database.getElements();

        Assert.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void moreComplexFunctionalTest() throws OperationNotSupportedException {
        this.database.add(6);
        this.database.remove();
        this.database.remove();
        this.database.add(5);
        this.database.remove();
        this.database.remove();

        Integer[] expectedArr = new Integer[]{1, 2, 3};
        Integer[] actualArr = this.database.getElements();

        Assert.assertArrayEquals(expectedArr, actualArr);
    }
}
