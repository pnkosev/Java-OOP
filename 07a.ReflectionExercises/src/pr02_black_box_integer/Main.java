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

        BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance();
        Field innerValue = blackBoxIntClass.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            String[] input = line.split("_");
            String operation = input[0];
            int value = Integer.parseInt(input[1]);

            switch (operation) {
                case "add":
                    Method add = blackBoxIntClass.getDeclaredMethod("add", int.class);
                    add.setAccessible(true);
                    add.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "subtract":
                    Method subtract = blackBoxIntClass.getDeclaredMethod("subtract", int.class);
                    subtract.setAccessible(true);
                    subtract.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "divide":
                    Method divide = blackBoxIntClass.getDeclaredMethod("divide", int.class);
                    divide.setAccessible(true);
                    divide.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "multiply":
                    Method multiply = blackBoxIntClass.getDeclaredMethod("multiply", int.class);
                    multiply.setAccessible(true);
                    multiply.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "rightShift":
                    Method rightShift = blackBoxIntClass.getDeclaredMethod("rightShift", int.class);
                    rightShift.setAccessible(true);
                    rightShift.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
                case "leftShift":
                    Method leftShift = blackBoxIntClass.getDeclaredMethod("leftShift", int.class);
                    leftShift.setAccessible(true);
                    leftShift.invoke(blackBoxInt, value);
                    System.out.println(innerValue.get(blackBoxInt));
                    break;
            }
        }
    }
}
