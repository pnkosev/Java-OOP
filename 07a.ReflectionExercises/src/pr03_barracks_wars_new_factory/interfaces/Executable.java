package pr03_barracks_wars_new_factory.interfaces;

import jdk.jshell.spi.ExecutionControl;

import javax.naming.OperationNotSupportedException;

public interface Executable {

	String execute() throws ExecutionControl.NotImplementedException, OperationNotSupportedException;

}
