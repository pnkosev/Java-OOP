package pr02_black_box_integer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<BlackBoxInt> blackBoxIntClass = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = blackBoxIntClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        BlackBoxInt blackBoxInt = constructor.newInstance();
        Field innerValue = blackBoxIntClass.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            String[] input = line.split("_");
            String operation = input[0];
            int value = Integer.parseInt(input[1]);

            Method op;

            switch (operation) {
                case "add":
                    op = blackBoxIntClass.getDeclaredMethod("add", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "subtract":
                    op = blackBoxIntClass.getDeclaredMethod("subtract", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "divide":
                    op = blackBoxIntClass.getDeclaredMethod("divide", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "multiply":
                    op = blackBoxIntClass.getDeclaredMethod("multiply", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "rightShift":
                    op = blackBoxIntClass.getDeclaredMethod("rightShift", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "leftShift":
                    op = blackBoxIntClass.getDeclaredMethod("leftShift", int.class);
                    op.setAccessible(true);
                    op.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
            }
        }
    }
}
