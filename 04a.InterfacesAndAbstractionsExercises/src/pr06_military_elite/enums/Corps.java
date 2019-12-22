package pr06_military_elite.enums;

public enum Corps {
    AIRFORCE("Airforces"),
    MARINE("Marines");

    private final String name;

    private Corps(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
