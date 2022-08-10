package kata.shapes;

/**
 * Created on 10.08.2022
 *
 * @author alexandrov
 */
public interface Shape extends Comparable<Shape> {

    double area();

    @Override
    default int compareTo(Shape o) {
        return Double.compare(this.area(), o.area());
    }
}
