package pr01_harvesting_fields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> aClass = RichSoilLand.class;
        Field[] fields = aClass.getDeclaredFields();
        String line;

        while (!(line = scanner.nextLine()).equals("HARVEST")) {
            switch (line) {
                case "private":
                    Arrays.stream(fields)
                            .filter(f -> Modifier.isPrivate(f.getModifiers()))
                            .forEach(f -> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
                    break;
                case "protected":
                    Arrays.stream(fields)
                            .filter(f -> Modifier.isProtected(f.getModifiers()))
                            .forEach(f -> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
                    break;
                case "public":
                    Arrays.stream(fields)
                            .filter(f -> Modifier.isPublic(f.getModifiers()))
                            .forEach(f -> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
                    break;
                case "all":
                    Arrays.stream(fields)
                            .forEach(f -> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
                    break;
            }
        }
    }
}
