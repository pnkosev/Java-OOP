package pr03_birthday_celebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Birthable> birthables = new ArrayList<>();

        String line;

        while (!(line = scanner.nextLine()).equals("End")) {
            String[] input = line.split(" ");
            String type = input[0];

            if (type.equals("Citizen")) {
                birthables.add(new Citizen(input[3], input[1], Integer.parseInt(input[2]), input[4]));
            } else if (type.equals("Pet")) {
                birthables.add(new Pet(input[1], input[2]));
            }
        }

        String year = scanner.nextLine();

        birthables.stream()
                .filter(e -> e.getBirthDate().endsWith(year))
                .forEach(b -> System.out.println(b.getBirthDate()));
    }
}
