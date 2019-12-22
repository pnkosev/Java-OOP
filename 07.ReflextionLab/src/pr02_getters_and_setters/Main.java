package pr02_getters_and_setters;

import pr02_getters_and_setters.Reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> reflection = Reflection.class;

        Method[] methods = reflection.getDeclaredMethods();

        Method[] getters = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get") && m.getReturnType() != void.class && m.getParameterCount() == 0)
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);

        Method[] setters = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("set") && m.getReturnType() == void.class && m.getParameterCount() == 1)
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);

        Arrays.stream(getters).forEach(g -> System.out.println(String.format("%s will return class %s", g.getName(), g.getReturnType().getName())));
        Arrays.stream(setters).forEach(s -> System.out.println(String.format("%s and will set field of class %s", s.getName(), Arrays.toString(s.getParameterTypes()))));
    }
}
