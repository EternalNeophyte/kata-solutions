package kata;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class MultipleOfPrimes {

    public String findThem(String numberLimit, int[] primes) {
        int limit = Integer.parseInt(numberLimit);
        var stopSet = createStopSet(primes);
        long result = 0;
        for(int prime : primes) {
            int multiple = prime;
            while (multiple < limit) {
                if(!stopSet.contains(multiple))
                    result += multiple;
                multiple += prime;
            }
        }
        int additional = stopSet.stream().filter(i -> i < limit).mapToInt(i -> i).sum();
        return String.valueOf(result + additional);
    }

    private Set<Integer> createStopSet(int[] primes) {
        Set<Integer> stopSet = new HashSet<>();
        for(int i = 0; i < primes.length; i++) {
            for(int j = i + 1; j < primes.length; j++) {
                stopSet.add(primes[i] * primes[j]);
            }
        }
        return stopSet;
    }

}
