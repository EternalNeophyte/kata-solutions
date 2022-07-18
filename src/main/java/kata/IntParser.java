package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <a href="https://www.codewars.com/kata/525c7c5ab6aecef16e0001a5/java">Kata details</a>
 */
public final class IntParser {

    public static final Map<String, Integer> BASES = Map.of(
            "zero", 0,
            "hundred", 100,
            "thousand", 1_000,
            "million", 1_000_000
    );
    public static final Map<String, Integer> NUMBERS = new HashMap<>();

    static {
        NUMBERS.put("one", 1);
    }

    public static int parseInt(String numStr) {
/*        AtomicInteger result = new AtomicInteger(), basedValue = new AtomicInteger(1);
        for(String key : numStr.split("\\s+|-|and")) {
            Optional.ofNullable(NUMBERS.get(key))
                    .ifPresentOrElse(basedValue::addAndGet, () -> {
                        Optional.ofNullable(BASES.get(key))
                                .
                    })
            int value = ;
            if(value != 0) {
                basedValue += value;
            }
            else {
                value = ;
                result += basedValue * value;
                basedValue = 0;
            }
        }
        return result + basedValue;*/
        return 0;
    }

    interface WordNumeral {
        int apply(int input);
    }

    enum Base implements WordNumeral {
        HUNDRED(100);

        final int value;

        Base(int value) {
            this.value = value;
        }

        @Override
        public int apply(int input) {
            return input * value;
        }
    }

    enum Fraction implements WordNumeral {
        ONE(1),
        TWO(2),
        ;
        final int value;

        Fraction(int value) {
            this.value = value;
        }

        @Override
        public int apply(int input) {
            return input + value;
        }
    }
}
