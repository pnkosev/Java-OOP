package pr06_military_elite.enums;

public enum MissionState {
    IN_PROGRESS("inProgress"),
    FINISHED("Finished");

    private String state;

    private MissionState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
