package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTests {
    private String[] input;
    private final String personA = "Pesho";
    private final String personB = "Gosho";
    private ListIterator listIterator;

    @Before
    public void setup() throws OperationNotSupportedException {
        this.input = new String[] { this.personA, this.personB };
        this.listIterator = new ListIterator(this.input);
    }

    @Test
    public void shouldReturnTrueIfHasNextElement() {
        boolean hasNext = this.listIterator.hasNext();

        Assert.assertTrue(hasNext);
    }

    @Test
    public void shouldReturnFalseIfHasNoNextElement() {
        this.listIterator.move();
        boolean hasNext = this.listIterator.hasNext();

        Assert.assertFalse(hasNext);
    }

    @Test
    public void shouldReturnTrueIfMoveWithNextElementPresent() {
        boolean hasMoved = this.listIterator.move();

        Assert.assertTrue(hasMoved);
    }

    @Test
    public void shouldReturnFalseIfMoveWithoutNextElementPresent() {
        boolean hasMoved;
        hasMoved = this.listIterator.move();
        hasMoved = this.listIterator.move();

        Assert.assertFalse(hasMoved);
    }

    @Test
    public void shouldReturnElementOnCurrentPosition() {
        String actual = this.listIterator.print();

        Assert.assertEquals(this.personA, actual);
    }
}
