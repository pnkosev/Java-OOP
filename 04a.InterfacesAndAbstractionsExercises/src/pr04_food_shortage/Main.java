package pr04_food_shortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Citizen> citizenMap = new HashMap<>();
        Map<String, Rebel> rebelMap = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        String[] line;

        for (int i = 0; i < n; i++) {
            line = scanner.nextLine().split(" ");
            if (line.length == 3) {
                rebelMap.put(line[0], new Rebel(line[0], Integer.parseInt(line[1]), line[2]));
            } else {
                citizenMap.put(line[0], new Citizen(line[0], Integer.parseInt(line[1]), line[2], line[3]));
            }
        }

        String name;

        while (!(name = scanner.nextLine()).equals("End")) {
            if (citizenMap.containsKey(name)) {
                citizenMap.get(name).buyFood();
            } else if (rebelMap.containsKey(name)) {
                rebelMap.get(name).buyFood();
            }
        }

        int citizenFoodSum = citizenMap.values().stream().mapToInt(Citizen::getFood).sum();
        int rebelFoodSum = rebelMap.values().stream().mapToInt(Rebel::getFood).sum();

        System.out.println(citizenFoodSum + rebelFoodSum);
    }
}
