package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<Integer> list;

    @Before
    public void setup() {
        this.list = new CustomLinkedList<>();
    }

    @Test
    public void creatingLinkedListShouldHaveZeroCount() throws IllegalAccessException, NoSuchFieldException {
        Field field = CustomLinkedList.class.getDeclaredField("count");

        field.setAccessible(true);
        int countValue = (int) field.get(this.list);

        assertNotNull("No such field", field);
        assertEquals("New CustomLinkedList did not have a count of zero", 0, countValue);
    }

    @Test
    public void shouldAddCorrectly() {
        Integer expected = 7;
        this.list.add(expected);
        Integer actual = this.list.get(0);
        assertEquals("The element was not added correctly", expected, actual);
    }

    @Test
    public void shouldIncreaseCountUponAdd() throws NoSuchFieldException, IllegalAccessException {
        Integer expected = 7;
        this.list.add(expected);
        Field field = CustomLinkedList.class.getDeclaredField("count");

        field.setAccessible(true);
        int countValue = (int) field.get(this.list);
        assertEquals("Count was not increased upon add", 1, countValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTryingToGetFromEmptyListWithIndexZero() {
        this.list.get(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTryingToGetFromEmptyListWithNegativeIndex() {
        this.list.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTryingToGetFromEmptyListWithPositiveIndex() {
        this.list.get(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTryingToSetWithNegativeIndex() {
        this.list.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTryingToSetWithIndexGreaterThanLength() {
        this.list.get(1);
    }

    @Test
    public void shouldSetCorrectlyIfCorrectIndex() {
        Integer testValue = 6;
        this.list.add(testValue);

        Integer expected = 7;

        this.list.set(0, 7);
        Integer actual = this.list.get(0);

        assertEquals("Item was not set correctly", expected, actual);
    }

    @Test
    public void shouldReturnMinusOneIfNoSuchElement() {
        int index = this.list.indexOf(7);

        assertEquals("It did not return -1 for non existing element in the list", -1, index);
    }

    @Test
    public void shouldReturnIndexOfElement() {
        this.list.add(7);
        int index = this.list.indexOf(7);
        assertEquals("It did not return the correct index of the item", 0, index);
    }

    @Test
    public void shouldReturnTrueIfContainingItem() {
        this.list.add(7);

        boolean isContained = this.list.contains(7);

        assertTrue("It does not return correctly that it was contained", isContained);
    }

    @Test
    public void shouldReturnFalseIfContainingItem() {
        this.list.add(7);

        boolean isContained = this.list.contains(8);

        assertFalse("It does not return correctly that it was not contained", isContained);
    }

    @Test
    public void shouldRemoveCorrectlyFromIndex() {
        Integer expected = 7;
        this.list.add(expected);
        Integer actual = this.list.removeAt(0);

        assertEquals("The return number from the removeAt was not correct", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfRemovingAtNegativeIndex() {
        this.list.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfRemovingAtIndexZeroFromEmptyList() {
        this.list.removeAt(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfRemovingAtIndexGreaterThanListsCount() {
        this.list.add(7);
        this.list.removeAt(1);
    }

    @Test
    public void shouldReturnMinusOneIfNoItemWasRemoved() {
        int index = this.list.remove(7);

        assertEquals("Non existing items should return -1", -1, index);
    }

    @Test
    public void shouldReturnIndexOfTheItemThatWasRemoved() {
        this.list.add(7);
        int index = this.list.remove(7);

        assertEquals("The index of the removed item was incorrect", 0, index);
    }
}