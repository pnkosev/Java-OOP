package pr06_military_elite.models;

public class RepairImpl implements Repair {
    private String part;
    private int hours;

    public RepairImpl(String part, int hours) {
        this.part = part;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return String.format("Part Name: %s Hours Worked: %d", this.part, this.hours);
    }
}
