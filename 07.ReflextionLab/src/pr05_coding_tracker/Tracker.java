package pr05_coding_tracker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Tracker {
    @Author(name = "George")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }

    @Author(name = "Peter")
    public static void printMethodsByAuthor(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m: methods) {
            Annotation annotation = m.getAnnotation(Author.class);

            System.out.println(((Author) annotation).name() + ": " + m.getName() + "()");
        }
    }
}
