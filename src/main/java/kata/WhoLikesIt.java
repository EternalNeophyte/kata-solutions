package kata;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <a href="https://www.codewars.com/kata/5266876b8f4bf2da9b000362/java">Kata details</a>
 */
public final class WhoLikesIt {

    public static String whoLikesIt(String... names) {
        return switch (names.length) {
            case 0 -> "no one likes this";
            case 1 -> names[0] + " likes this";
            case 2 -> join(names, " and ");
            case 3 -> join(names, ", ", " and ");
            default -> {
                names[2] = "and %d others".formatted(names.length - 2);
                yield join(names, ", ", " ");
            }
        };
    }

    public static String join(String[] names, String... delimiters) {
        StringBuilder sb = new StringBuilder(names[0]);
        for(int i = 0; i < delimiters.length; i++) {
            sb.append(delimiters[i]).append(names[i + 1]);
        }
        return sb.append(" like this").toString();
    }

}
