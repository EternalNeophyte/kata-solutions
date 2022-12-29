package kata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created on 05.12.2022
 *
 * @author alexandrov
 */
public class LongestSlideDown {

    public static int longestSlideDown(int[][] pyramid) {
        //Вычислить сумму максимумов для каждого шага.
        //Обходить пирамиду рекурсивно: если разница между путями больше оставшейся суммы максимумов, наименьший путь "обрубается"
        var maxGainLeft = Stream.of(pyramid)
            .mapToInt(row -> IntStream.of(row).max().orElse(0))
            .collect(ArrayList::new, (list, max) -> list.add(0, max), Collection::addAll);

        return 0;
    }

    public int step(int[][] pyramid, List<Integer> maxGainLeft, int rowIndex, int pos, int routeSum, AtomicInteger maxRoute, StringBuilder track) {
        if(rowIndex == pyramid.length) {
            return routeSum;
        }
        if(maxRoute.get() - routeSum > maxGainLeft.get(rowIndex)) {
            System.out.println(track + " died");
            return 0;
        }
        int num = pyramid[rowIndex][pos];
        track.append(" - (").append(rowIndex).append(") ").append(num);
        routeSum += num;
        if(routeSum > maxRoute.get()) {
            maxRoute.set(routeSum);
        }
        step(pyramid, maxGainLeft, ++rowIndex, pos, routeSum, maxRoute, track);
        step(pyramid, maxGainLeft, ++rowIndex, ++pos, routeSum, maxRoute, track);
        return 0;
    }
}
