package pr03_birthday_celebrations;

public class Citizen extends AbstractIdentifiable implements Birthable{
    private String name;
    private int age;
    private String birthDate;

    public Citizen(String id, String name, int age, String birthDate) {
        super(id);
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }
}
