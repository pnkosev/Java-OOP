package pr03_barracks_wars_new_factory.core.commands;

import pr03_barracks_wars_new_factory.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMAND_PATH = "pr03_barracks_wars_new_factory.core.commands.";

    private UnitFactory factory;
    private Repository repository;

    public CommandInterpreterImpl(UnitFactory factory, Repository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public Executable interpretCommand(String[] data) {
        Executable executable = null;
        String command = getNormalizedName(data[0]);
        Class clazz = null;
        try {
            clazz = Class.forName(COMMAND_PATH + command);
            Constructor constructor = clazz.getDeclaredConstructor(String[].class);
            executable = (Executable) constructor.newInstance((Object) data);

            populateDependencies(executable);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return executable;
    }

    private void populateDependencies(Executable executable) throws IllegalAccessException {
        Field[] exeFields = executable.getClass().getDeclaredFields();
        Field[] currClazzFields = this.getClass().getDeclaredFields();

        for (Field requiredField : exeFields) {
            Inject annotation = null;
            try {
                annotation = requiredField.getAnnotation(Inject.class);
            } catch (ClassCastException e) {
                continue;
            }

            for (Field currField : currClazzFields) {
                if (requiredField.getType().equals(currField.getType())) {
                    requiredField.set(executable, currField.get(this));
                }
            }
        }
    }

    private String getNormalizedName(String name) {
        return name.toUpperCase().charAt(0) +
                name.substring(1);
    }
}
