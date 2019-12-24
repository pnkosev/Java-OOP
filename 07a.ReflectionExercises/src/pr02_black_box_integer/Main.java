package pr02_black_box_integer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<BlackBoxInt> blackBoxIntClass = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = blackBoxIntClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        BlackBoxInt blackBoxInt = constructor.newInstance();
        Field innerValue = blackBoxIntClass.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        Method[] methods = blackBoxIntClass.getDeclaredMethods();

        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            String[] input = line.split("_");
            String operation = input[0];
            int value = Integer.parseInt(input[1]);

            Method method = Arrays.stream(methods)
                    .filter(f -> f.getName().equals(operation))
                    .findFirst().orElse(null);

            if (method != null) {
                method.setAccessible(true);
                method.invoke(blackBoxInt, value);
            }
            System.out.println(innerValue.get(blackBoxInt));
        }
    }
}