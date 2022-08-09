package kata;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public final class NextBiggerNumber {

    public static long nextBiggerNumber(long n)
    {
        Set<Long> candidates = new HashSet<>() {{ add(n); }};
        int[] digits = Arrays.stream(String.valueOf(n).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int start = 0, end = digits.length - 1;
        for(int i = end; i > start; i--) {
            if (digits[i] > digits[i - 1]) {
                swap(digits, i);
                start = i;
                i = end;
                candidates.add(numberFrom(digits));
                swap(digits, end);
                candidates.add(numberFrom(digits));
            }
        }
        List<Long> list = candidates.stream().sorted().toList();
        return list.size() <= 1 ? -1 : list.get(list.indexOf(n) + 1);
    }

    private static void swap(int[] digits, int i) {
        digits[i] ^= digits[i - 1];
        digits[i - 1] ^= digits[i];
        digits[i] ^= digits[i - 1];
    }

    private static long numberFrom(int[] digits) {
        return Arrays.stream(digits).reduce(0, (a, b) -> a * 10 + b);
    }
}
