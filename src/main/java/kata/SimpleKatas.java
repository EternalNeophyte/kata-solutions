package kata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.function.Function.identity;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

import static java.lang.Math.max;

/**
 * Created on 11.08.2022
 *
 * @author alexandrov
 */
public final class SimpleKatas {

    public static int countSmileys(List<String> arr) {
        return (int) arr.stream()
                .filter(s -> s.matches("[:;][-~]?[)D]"))
                .count();
    }

    static String encode(String word) {
        var symbols = word.toLowerCase().split("");
        var groups = Arrays.stream(symbols)
                .collect(groupingBy(identity(), counting()));
        return Arrays.stream(symbols)
                .map(s -> groups.get(s) == 1 ? "(" : ")")
                .collect(joining());
    }

    public static String regexBelow(long n) {
        if(n <= 1)
            return "[\\D\\W]";
        if(n < 10)
            return "[0-" + max(n - 1, 0) + "]";

        var digits = String.valueOf(--n).split("");
        AtomicInteger lastDigit = new AtomicInteger();

        int tailLength = max(digits.length - 2, 0);
        String additionalCaptureGroups = "([1-%s][0-%d][\\d]{0,%d})|([1-9][\\d]{0,%d})"
                .formatted(digits[0], max(parseInt(digits[1]) - 1, 0), tailLength, tailLength);

        return Arrays.stream(digits)
                .dropWhile("0"::equals)
                .filter(not("[-\\w]"::matches))
                .collect(StringBuilder::new,
                        (sb, digit) -> {
                            if(sb.isEmpty())
                                sb.append("([1-").append(digit).append("]");
                            else
                                sb.append("((?<=[0-").append(max(lastDigit.decrementAndGet(), 0))
                                    .append("])\\d|[0-").append(digit).append("])");
                            lastDigit.set(parseInt(digit));
                        },
                        StringBuilder::append
                )
                .append(")|")
                .append(additionalCaptureGroups)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(1337 + " - " + regexBelow(1337));
        System.out.println(67345 + " - " +  regexBelow(67345));
        System.out.println(Pattern.compile("[\\D\\W]").matcher(String.valueOf(0)).find());
    }

    static String toCamelCase(String s){
        return Pattern.compile("(?<=[\\W_])[\\w]")
                .matcher(s)
                .replaceAll(m -> m.group().substring(1).toUpperCase());
    }
}
