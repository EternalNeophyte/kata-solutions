package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public record Rectangle(double width, double height) implements Shape {

    @Override
    public double area() {
        return width * height;
    }
}
