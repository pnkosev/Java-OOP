package pr03_barracks_wars_new_factory.core.commands;

import pr03_barracks_wars_new_factory.interfaces.Inject;
import pr03_barracks_wars_new_factory.interfaces.Repository;

public class Report extends Command {
    @Inject
    Repository repository;

    public Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
