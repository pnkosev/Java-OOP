package pr03_barracks_wars_new_factory.core.commands;

import jdk.jshell.spi.ExecutionControl;
import pr03_barracks_wars_new_factory.interfaces.Inject;
import pr03_barracks_wars_new_factory.interfaces.Repository;

import javax.naming.OperationNotSupportedException;

public class Retire extends Command {
    @Inject
    Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String type = this.getData()[1];
        String result;

        try {
            this.repository.removeUnit(type);
            result = type + " retired!";
        } catch (OperationNotSupportedException | ExecutionControl.NotImplementedException e) {
            result = e.getMessage();
        }

        return  result;
    }
}
