package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public record Circle(double radius) implements Shape {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
