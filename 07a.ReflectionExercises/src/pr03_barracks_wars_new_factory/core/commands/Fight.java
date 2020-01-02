package pr03_barracks_wars_new_factory.core.commands;

public class Fight extends Command {
    public Fight(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
