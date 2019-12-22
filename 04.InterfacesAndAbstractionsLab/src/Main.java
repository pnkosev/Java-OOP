//import car.*;
import person.Bulgarian;
import person.Chinese;
import person.European;
import person.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //    exercise 1
//    public static void main(String[] args) {
//        Car seat = new Seat("Leon", "gray", 110, "Spain");
//
//        System.out.println(String.format(
//                "%s is %s color and have %s horse power",
//                seat.getModel(),
//                seat.getColor(),
//                seat.getHorsePower()));
//        System.out.println(seat.toString());
//    }

    //    exercise 2
//    public static void main(String[] args) {
//        Sellable seat = new Seat("Leon", "Gray", 110, "Spain", 11111.1);
//        Rentable audi = new Audi("A4", "Gray", 110, "Germany", 3, 99.9);
//        printCarInfo(seat);
//        printCarInfo(audi);
//    }
//
//    private static void printCarInfo(Car car) {
//        System.out.println(String.format(
//                "%s is %s color and have %s horse power",
//                car.getModel(),
//                car.getColor(),
//                car.getHorsePower()));
//        System.out.println(car.toString());
//    }

    //    exercise 3 and 4
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Bulgarian("Pesho"));
        persons.add(new European("Pesho"));
        persons.add(new Chinese("Pesho"));
        for (Person person : persons) {
            print(person);
        }
    }
    private static void print(Person person) {
        System.out.println(person.sayHello());
    }
}
