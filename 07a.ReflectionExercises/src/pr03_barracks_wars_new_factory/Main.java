package pr03_barracks_wars_new_factory;

import pr03_barracks_wars_new_factory.interfaces.Repository;
import pr03_barracks_wars_new_factory.interfaces.Runnable;
import pr03_barracks_wars_new_factory.interfaces.UnitFactory;
import pr03_barracks_wars_new_factory.core.Engine;
import pr03_barracks_wars_new_factory.core.factories.UnitFactoryImpl;
import pr03_barracks_wars_new_factory.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
