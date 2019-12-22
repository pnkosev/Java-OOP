package identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Identifiable> ids = new ArrayList<>();

        String command;
        while (!(command = scanner.nextLine()).equals("End")) {
            String[] input = command.split(" ");

            if (input.length == 2) {
                ids.add(new Robot(input[0], input[1]));
            } else {
                ids.add(new Citizen(input[0], Integer.parseInt(input[1]), input[2]));
            }
        }

        String idEndingWith = scanner.nextLine();

        ids.stream()
                .filter(e -> e.getId().endsWith(idEndingWith))
                .forEach(e -> System.out.println(e.getId()));
    }
}
