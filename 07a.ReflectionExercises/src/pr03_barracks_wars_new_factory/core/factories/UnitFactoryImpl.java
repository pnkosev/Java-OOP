package pr03_barracks_wars_new_factory.core.factories;

import pr03_barracks_wars_new_factory.interfaces.Unit;
import pr03_barracks_wars_new_factory.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"pr03_barracks_wars_new_factory.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
		// TODO: implement for problem 3
		throw new ExecutionControl.NotImplementedException("message");
	}
}
