package kata;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
/*        System.out.println(incrementString("wqef00423653468478897679863457647578768688923"));
        System.out.println(incrementString("wqef"));
        System.out.println(incrementString("wqef0023"));
        System.out.println(incrementString("wqef000"));
        System.out.println(incrementString("foobar099"));
        System.out.println(incrementString("foobar99"));
        System.out.println(incrementString("<J*DHTS6Nu64uK3'QY\\\\[0"));
        System.out.println(incrementString(""));*/
        System.out.println(incrementString("rS>I>N)|!E+TxLouRP~\\495499481666650942316873546411711"));

    }

    static String toCamelCase(String s){
        return Pattern.compile("(?<=[\\W_])[\\w]")
                .matcher(s)
                .replaceAll(m -> m.group().substring(1).toUpperCase());
    }

    public static String incrementString(String str) {
       return Pattern.compile("([\\.\\\\]*?)(\\\\*)([0]*)(\\d*?)(\\d{0,18}$)")
                .matcher(str)
                .replaceFirst(matchResult -> {
                    StringBuilder result = new StringBuilder(matchResult.group(1)).append(matchResult.group(2));
                    String zeros = matchResult.group(3), overflow = matchResult.group(4), number = matchResult.group(5);
                    if(number.isEmpty()) {
                        if(!zeros.isEmpty())
                            result.append(zeros.substring(1));
                        result.append("1");
                    }
                    else {
                        String incremented = String.valueOf(Long.parseLong(number) + 1);
                        int lenDiff = incremented.length() - number.length();
                        if(!zeros.isEmpty())
                            result.append(zeros.substring(0, zeros.length() - lenDiff));
                        result.append(overflow);
                        if(number.startsWith("0"))
                            result.append(number.split("[^0]+")[0]);
                        result.append(incremented);
                    }
                    return result.toString();
                })
               .replaceAll("\\\\", "\\\\");
    }
}
