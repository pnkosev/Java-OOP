package pr03_barracks_wars_new_factory.core;

import jdk.jshell.spi.ExecutionControl;
import pr03_barracks_wars_new_factory.core.commands.CommandInterpreterImpl;
import pr03_barracks_wars_new_factory.interfaces.Runnable;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    CommandInterpreterImpl commandInterpreter;

    public Engine(CommandInterpreterImpl commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String result = this.commandInterpreter.interpretCommand(data).execute();
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ExecutionControl.NotImplementedException | OperationNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
