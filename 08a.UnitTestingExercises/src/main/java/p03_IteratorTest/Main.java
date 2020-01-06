package p03_IteratorTest;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        Scanner scanner = new Scanner(System.in);

        String[] initialLine = scanner.nextLine().split("\\s+");

        ListIterator iterator = new ListIterator(Arrays.stream(initialLine).skip(1).toArray(String[]::new));
        String command;

        while (!(command = scanner.nextLine().toLowerCase()).equals("end")) {
            switch (command.toLowerCase()) {
                case "hasnext":
                    System.out.println(iterator.hasNext());
                    break;
                case "move":
                    System.out.println(iterator.move());
                    break;
                case "print":
                    System.out.println(iterator.print());
                    break;
            }
        }
    }
}
