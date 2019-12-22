package pr02_shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        calculatePerimeter();
        calculateArea();
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(2 * Math.PI * this.radius);
    }

    @Override
    protected void calculateArea() {
        setArea(Math.PI * Math.pow(this.radius, 2));
    }

    public final Double getRadius() {
        return this.radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
