package kata;

import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class PigLatinAndOrder {

    public static String pigIt(String str) {
        return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
    }

    public static String order(String words) {
        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D+", ""))))
                .collect(Collectors.joining(" "));
    }

    public static int duplicateCount(String text) {
        return (int) text.toLowerCase()
                        .chars()
                        .boxed()
                        .collect(groupingBy(identity(), counting()))
                        .values()
                        .stream()
                        .filter(occurences -> occurences > 1)
                        .count();
    }

    public static boolean isValid(char[] walk) {
        if(walk.length != 10)
            return false;
        var moves = CharBuffer.wrap(walk)
                .chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));
        return Objects.equals(moves.get('n'), moves.get('s')) &&
                Objects.equals(moves.get('w'), moves.get('e'));
    }


}
