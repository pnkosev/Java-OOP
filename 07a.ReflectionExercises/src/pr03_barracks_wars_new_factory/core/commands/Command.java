package pr03_barracks_wars_new_factory.core.commands;

import pr03_barracks_wars_new_factory.interfaces.Executable;

public abstract class Command implements Executable {
    private String[] data;

    protected Command(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return this.data;
    }
}
