package pr01_reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Class reflection = Reflection.class;
        System.out.println(reflection);

        Class superClass = reflection.getSuperclass();
        System.out.println(superClass);

        Class[] classInterfaces = reflection.getInterfaces();
        Arrays.stream(classInterfaces).forEach(System.out::println);

        Object reflectionInstance = reflection.getConstructor().newInstance();
        System.out.println(reflectionInstance.toString());
    }
}
