package kata;


import java.util.List;
import java.util.stream.LongStream;

public final class SumDigPower {

    public static List<Long> sumDigPow(long a, long b) {
        return LongStream.rangeClosed(a, b)
                        .filter(SumDigPower::isEuristic)
                        .boxed()
                        .toList();
    }

    private static boolean isEuristic(long number) {
        String[] digits = String.valueOf(number).split("");
        long result = 0;
        for(int i = 0; i < digits.length; i++) {
            result += Math.pow(Integer.parseInt(digits[i]), i + 1);
        }
        return number == result;
    }
}
