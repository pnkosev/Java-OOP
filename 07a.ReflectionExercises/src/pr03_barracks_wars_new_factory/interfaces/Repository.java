package pr03_barracks_wars_new_factory.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface Repository {

	void addUnit(pr03_barracks_wars_new_factory.interfaces.Unit unit);

	String getStatistics();

	void removeUnit(String unitType) throws ExecutionControl.NotImplementedException;
}
