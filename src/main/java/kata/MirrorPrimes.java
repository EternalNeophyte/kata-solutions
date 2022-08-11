package kata;

import java.util.stream.IntStream;

/**
 * Created on 11.08.2022
 *
 * @author alexandrov
 */
public final class MirrorPrimes {

    private static final StringBuilder reverser = new StringBuilder();

    public static int primesCount(int a, int b){
        if(a > b || a < 0)
            return -1;
        return (int) IntStream
                    .rangeClosed(a + 1, b)
                    .filter(i -> isPrime(i) && isPrime(mirrorOf(i)))
                    .count();
    }

    public static int mirrorOf(int number) {
        return reverser.delete(0, reverser.length())
                    .append(number)
                    .reverse()
                    .toString()
                    .transform(Integer::parseInt);
    }

    public static boolean isPrime(int number) {
        for(int divisor = 2; divisor <= number / 2; divisor++) {
            if(number % divisor == 0) return false;
        }
        return true;
    }
}
