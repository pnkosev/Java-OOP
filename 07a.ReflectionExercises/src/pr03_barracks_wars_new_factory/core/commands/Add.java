package pr03_barracks_wars_new_factory.core.commands;

import jdk.jshell.spi.ExecutionControl;
import pr03_barracks_wars_new_factory.interfaces.Inject;
import pr03_barracks_wars_new_factory.interfaces.Repository;
import pr03_barracks_wars_new_factory.interfaces.Unit;
import pr03_barracks_wars_new_factory.interfaces.UnitFactory;

public class Add extends Command {
    @Inject
    UnitFactory factory;
    @Inject
    Repository repository;

    public Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String unitType = this.getData()[1];
        Unit unitToAdd = this.factory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}
