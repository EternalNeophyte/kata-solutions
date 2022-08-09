package kata;

import java.util.Arrays;
import java.util.List;

public final class NextBiggerNumber {

    public static long nextBiggerNumber(long n)
    {
        int[] digits = Arrays.stream(String.valueOf(n).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = 0, end = digits.length - 1;
        boolean rearranged = false;
        for(int i = end; i > start; i--) {
            if(digits[i] > digits[i - 1]) {
                digits[i] ^= digits[i - 1];
                digits[i - 1] ^= digits[i];
                digits[i] ^= digits[i - 1];
                start = i;
                i = end;
                rearranged = true;
            }
        }
        return rearranged
                ? Arrays.stream(digits).reduce(0, (a, b) -> a * 10 + b)
                : -1;
    }

}
