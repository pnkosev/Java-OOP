package pr01_singleton;

import java.io.Serializable;
import java.util.Map;

public class SingletonDataContainer implements SingletonContainer, Serializable {
    private static volatile SingletonDataContainer instance;

    private SingletonDataContainer() {

        // Prevents from the reflection API
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of the class!");
        }
    }

    @Override
    public int getPopulation(Map<String, Integer> capitols, String name) {
        return capitols.get(name);
    }

    public static SingletonDataContainer getInstance() {
        if (instance == null) { // If there is no instance available- create new one
            synchronized (SingletonDataContainer.class) {
                if (instance == null) {
                    instance = new SingletonDataContainer();
                }
            }
        }

        return instance;
    }

    protected Object readResolve() {
        return instance;
    }
}
