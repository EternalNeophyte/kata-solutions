package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public record Square(double side) implements Shape {

    @Override
    public double area() {
        return side * side;
    }
}
