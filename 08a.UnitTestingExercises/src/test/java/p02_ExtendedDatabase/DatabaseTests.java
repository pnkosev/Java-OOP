package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTests {
    private Person personA;
    private Person personB;
    private Person personC;
    private Person[] people;
    private Database database;

    @Before
    public void setup() throws OperationNotSupportedException {
        this.personA = new Person(1, "PN");
        this.personB = new Person(2, "MM");
        this.people = fillPeople(this.personA, this.personB);
        this.database = new Database(this.people);
    }

    @Test
    public void shouldInitializeCorrectly() {
        Person[] actual = this.database.getElements();
        Assert.assertArrayEquals(this.people, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfNullParameter() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void shouldAddPersonCorrectly() throws OperationNotSupportedException {
        Person personC = new Person(3, "MK");
        this.database.add(personC);
        Person[] expected = new Person[] { this.personA, this.personB, personC };
        Person[] actual = this.database.getElements();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveCorrectly() throws OperationNotSupportedException {
        this.database.remove();
        Person[] expected = new Person[] { this.personA };
        Person[] actual = this.database.getElements();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfRemovingFromEmptyDatabase() throws OperationNotSupportedException {
        this.database.remove();
        this.database.remove();
        this.database.remove();
    }

    @Test
    public void shouldReturnPersonWhenSearchingById() throws OperationNotSupportedException {
        Person searchedPerson = this.database.findById(1);
        Assert.assertEquals(this.personA, searchedPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfPersonsIdIsNonExisting() throws OperationNotSupportedException {
        this.database.findById(3);
    }

    @Test
    public void shouldReturnPersonWhenSearchingByUsername() throws OperationNotSupportedException {
        Person searchedPerson = this.database.findByUsername("PN");
        Assert.assertEquals(this.personA, searchedPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfSearchingWithNonExistingUsername() throws OperationNotSupportedException {
        this.database.findByUsername("MK");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfSearchingWithNullUsername() throws OperationNotSupportedException {
        this.database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowIfSearchingByUsernameCaseSensitivelyReturnsNoResult() throws OperationNotSupportedException {
        this.database.findByUsername("pn");
    }

    private Person[] fillPeople(Person... args) {
        Person[] people = new Person[args.length];
        for (int i = 0; i < people.length; i++) {
            people[i] = args[i];
        }

        return people;
    }
}
