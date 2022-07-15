package kata;

import static java.util.stream.IntStream.*;

/**
 * <a href="https://www.codewars.com/kata/5839edaa6754d6fec10000a2/java">Kata details</a>
 */
public final class MissingLetter {

    public static char findMissingLetter(char[] array)
    {
        return (char)   // This XOR solution is really weird
                concat(rangeClosed(array[0], array[array.length - 1]), new String(array).chars())
                .reduce((ch1, ch2) -> ch1 ^ ch2)
                .orElseThrow();
    }
}
