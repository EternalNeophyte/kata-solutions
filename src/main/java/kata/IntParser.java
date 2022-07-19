package kata;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

/**
 * <a href="https://www.codewars.com/kata/525c7c5ab6aecef16e0001a5/java">Kata details</a>
 */
public final class IntParser {

    public static final Map<String, Integer> NUMBERS;
    public static final Map<String, Integer> BASES;

    static {
        var definitions = new String[] {
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
                "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };
        AtomicInteger value = new AtomicInteger(1);
        NUMBERS = Arrays.stream(definitions)
                        .collect(toUnmodifiableMap(identity(),
                                definition -> value.getAndAdd(definition.endsWith("ty") ? 10 : 1))
                        );
        BASES = Map.of(
                "zero", 0,
                "hundred", 100,
                "thousand", 1_000,
                "million", 1_000_000
        );
    }

    public static int parseInt(String numStr) {
        int result = 0, based = 0;
        for(String definition : numStr.split("[\\s-]+(and)?[\\s-]*")) {
            Integer current = NUMBERS.get(definition);
            if(current != null) {
                based += current;
            }
            else {
                current = Objects.requireNonNull(BASES.get(definition), "Unknown number: " + definition);
                if(based != 0) {
                    based += result;
                    based *= current;
                    if(based > result) {
                        result = based;
                    }
                    else {
                        result += based;
                    }
                }
                else {
                    result = current;
                }
                based = 0;
            }
        }
        return result + based;
    }

}
