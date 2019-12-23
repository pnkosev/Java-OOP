package pr01_harvesting_fields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> aClass = RichSoilLand.class;
        Field[] fields = aClass.getDeclaredFields();
        String line;

        while (!(line = scanner.nextLine()).equals("HARVEST")) {
            for (Field f: fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.equals(line) || line.equals("all")) {
                    System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName());
                }
            }
        }
    }
}
