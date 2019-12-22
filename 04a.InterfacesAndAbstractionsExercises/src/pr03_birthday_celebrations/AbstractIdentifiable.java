package pr03_birthday_celebrations;

public class AbstractIdentifiable implements Identifiable {
    private String id;

    public AbstractIdentifiable(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
