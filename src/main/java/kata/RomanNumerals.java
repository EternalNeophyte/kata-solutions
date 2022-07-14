package kata;

import java.util.*;
import java.util.function.Function;

/**
 * <a href="https://www.codewars.com/kata/51b66044bce5799a7f000003/java">Kata details</a>
 */
public final class RomanNumerals {

    public static String toRoman(int n) {
        if(n < 1 || n >= 4000) {
            throw new IllegalArgumentException(n + " is out of range");
        }
        StringBuilder romanBuilder = new StringBuilder();
        int remainder, base = 1;
        while(n > 0) {
            remainder = n % 10;
            switch(remainder) {
                case 1, 2, 3 -> romanBuilder.insert(0, RomanDigit.of(base).toString().repeat(remainder));
                case 4 -> romanBuilder.insert(0, RomanDigit.of(base * 5)).insert(0, RomanDigit.of(base));
                case 5, 6, 7, 8 -> romanBuilder.insert(0, RomanDigit.of(base).toString().repeat(remainder - 5))
                                                .insert(0, RomanDigit.of(base * 5));
                case 9 -> romanBuilder.insert(0, RomanDigit.of(base * 10)).insert(0, RomanDigit.of(base));
            }
            n /= 10;
            base *= 10;
        }
        return romanBuilder.toString();
    }

    public static int fromRoman(String romanNumeral) {
        char[] romanChars = romanNumeral.toUpperCase().toCharArray();
        int result = 0;
        RomanDigit current, next = RomanDigit.of(romanChars[0]);
        for(int pos = 0; pos < romanChars.length - 1; pos++) {
            current = RomanDigit.of(romanChars[pos]);
            next = RomanDigit.of(romanChars[pos + 1]);
            if (current.weight - next.weight >= 0)
                result += current.value;
            else
                result -= current.value;
        }
        result += next.value;
        return result;
    }

    enum RomanDigit {
        I('I',1, 1),
        V('V', 5, 2),
        X('X', 10, 3),
        L('L', 50, 4),
        C('C', 100, 5),
        D('D', 500, 6),
        M('M', 1000, 7);

        final char symbol;
        final int value, weight;

        RomanDigit(char symbol, int value, int weight) {
            this.symbol = symbol;
            this.value = value;
            this.weight = weight;
        }

        static RomanDigit of(char symbol) {
            return ofAttribute(symbol, digit -> digit.symbol);
        }

        static RomanDigit of(int value) {
            return ofAttribute(value, digit -> digit.value);
        }

        private static <T> RomanDigit ofAttribute(T attr, Function<RomanDigit, T> attrFactory) {
            for(var digit : values()) {
                if(attrFactory.apply(digit).equals(attr)) return digit;
            }
            throw new NoSuchElementException("Roman digit with attr '" + attr + "' not found");
        }

        @Override
        public String toString() {
            return Character.toString(symbol);
        }
    }
}
