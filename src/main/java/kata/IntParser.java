package kata;

/**
 * <a href="https://www.codewars.com/kata/525c7c5ab6aecef16e0001a5/java">Kata details</a>
 */
public final class IntParser {

    public static int parseInt(String numStr) {
        // Your code here!
        return -1;
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
