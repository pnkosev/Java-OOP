package pr02_shapes;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5.0, 10.0);
        Shape circle = new Circle(3.0);

        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());
        System.out.println(circle.getPerimeter());
        System.out.println(circle.getArea());
    }
}
