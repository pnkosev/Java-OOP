package pr03_barracks_wars_new_factory.core.factories;

import jdk.jshell.spi.ExecutionControl;
import pr03_barracks_wars_new_factory.interfaces.Unit;
import pr03_barracks_wars_new_factory.interfaces.UnitFactory;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"pr03_barracks_wars_new_factory.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
		Unit unit = null;

		try {
			Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			unit = (Unit) clazz.getDeclaredConstructor().newInstance();

			return unit;
		} catch (Exception e) {
			throw new ExecutionControl.NotImplementedException("message");
		}

	}
}
