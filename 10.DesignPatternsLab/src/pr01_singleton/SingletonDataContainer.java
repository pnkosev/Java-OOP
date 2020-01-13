package pr01_singleton;

import java.util.HashMap;
import java.util.Map;

public class SingletonDataContainer implements SingletonContainer {
    private static SingletonDataContainer instance;
    private Map<String, Integer> capitals;

    private SingletonDataContainer() {
        this.capitals = new HashMap<>();
        System.out.println("Initializing singleton object");
    }

    @Override
    public int getPopulation(Map<String, Integer> capitols, String name) {
        return capitols.get(name);
    }

    public static SingletonDataContainer getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new SingletonDataContainer();
        return instance;
    }
}
