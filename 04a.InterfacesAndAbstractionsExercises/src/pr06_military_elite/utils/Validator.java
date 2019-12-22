package pr06_military_elite.utils;

public class Validator {
    private Validator() {

    }

    public static boolean isCorpsValid(String corps) {
        if (!corps.equals("Airforces") && !corps.equals("Marines")) {
            return false;
        }

        return true;
    }

    public static boolean isStateValid(String state) {
        if (!state.equals("Finished") && !state.equals("inProgress")) {
            return false;
        }

        return true;
    }
}
