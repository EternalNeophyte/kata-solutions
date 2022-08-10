package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public record Triangle(double base, double height) implements Shape {

    @Override
    public double area() {
        return base * height / 2;
    }
}
