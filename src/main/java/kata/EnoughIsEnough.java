package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class EnoughIsEnough {

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        Map<Integer, Integer> allOccurences = new HashMap<>();
        return Arrays.stream(elements)
                    .filter(e -> allOccurences.merge(e, 1, Integer::sum) <= maxOccurrences)
                    .toArray();
    }
}
