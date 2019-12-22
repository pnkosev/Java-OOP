package pr03_high_quality_mistakes;

import pr04_create_annotations.Subject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> reflection = Reflection.class;

        Arrays.stream(reflection.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.printf("%s must be private!%n", f.getName()));

        Arrays.stream(reflection.getDeclaredMethods())
                .filter(g -> g.getName().startsWith("get") && g.getParameterCount() == 0 && !Modifier.isPublic(g.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(g -> System.out.printf("%s have to be public!%n", g.getName()));

        Arrays.stream(reflection.getDeclaredMethods())
                .filter(s -> s.getName().startsWith("set") && s.getParameterCount() == 1 && !Modifier.isPrivate(s.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(s -> System.out.printf("%s have to be private!%n", s.getName()));
    }
}
