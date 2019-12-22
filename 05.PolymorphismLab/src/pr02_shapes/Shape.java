package pr02_shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    protected Shape() {

    }

    protected abstract void calculatePerimeter();
    protected abstract void calculateArea();

    public Double getPerimeter() {
        return this.perimeter;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return this.area;
    }

    protected void setArea(Double area) {
        this.area = area;
    }
}
