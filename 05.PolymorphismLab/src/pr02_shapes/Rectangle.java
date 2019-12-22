package pr02_shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
        calculatePerimeter();
        calculateArea();
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(2 * (this.height + this.width));
    }

    @Override
    protected void calculateArea() {
        setArea(this.height * this.width);
    }

    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return this.width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
