package kata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

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
        AtomicInteger lastDigit = new AtomicInteger();
        var digits = String.valueOf(n).split("");
        return Arrays.stream(digits)
                .dropWhile("0"::equals)
                .collect(StringBuilder::new,
                        (sb, digit) -> {
                            if(sb.isEmpty()) {
                                sb.append("([1-").append(digit).append("]");
                            }
                            else {
                                int bound = Math.max(lastDigit.decrementAndGet(), 0);
                                sb.append("((?<=[0-").append(bound).append("])\\d|[0-").append(digit).append("])");
                            }
                            lastDigit.set(Integer.parseInt(digit));
                        },
                        StringBuilder::append
                )
                .append(")|([1-9][\\d]{0,")
                .append(Math.max(digits.length - 2, 0)).append("})")
                .toString();
    }

    public static void main(String[] args) {
        var p = Pattern.compile("\\d((?<=[0-5])\\d|[0-6])");
        p.matcher("19 69 48").results().map(MatchResult::group).forEach(System.out::println);
        System.out.println(1007 + " - " + regexBelow(1007));
        System.out.println(67345 + " - " +  regexBelow(67345));
    }
}
