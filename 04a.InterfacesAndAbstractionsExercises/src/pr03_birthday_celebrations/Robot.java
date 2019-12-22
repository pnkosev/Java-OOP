package pr03_birthday_celebrations;

public class Robot extends AbstractIdentifiable {
    private String model;

    public Robot(String id, String model) {
        super(id);
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }
}
