package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public class ShapeHolder {

public interface Shape extends Comparable<Shape> {

    double area();

    @Override
    default int compareTo(Shape o) {
        return Double.compare(this.area(), o.area());
    }
}

public record Square(double side) implements Shape {

    @Override
    public double area() {
        return side * side;
    }
}

public record Rectangle(double width, double height) implements Shape {

    @Override
    public double area() {
        return width * height;
    }
}

public record Triangle(double base, double height) implements Shape {

    @Override
    public double area() {
        return base * height / 2;
    }
}

public record Circle(double radius) implements Shape {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public record CustomShape(double area) implements Shape { }
}
