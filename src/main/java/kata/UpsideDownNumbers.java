package kata;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created on 29.12.2022
 *
 * @author alexandrov
 */
public class UpsideDownNumbers {


    //1x filler = 0, 1, 8
    //11, 69, 96, 88
    //2x fillers = 00, 11, 88, 69, 96
    //1x1, 8x8, 6x9, 9x6 - 12
    //1xx1, 8xx8

    public static final Set<Character> SINGLE_FILLERS = Set.of('0', '1', '8');
    public static final Set<char[]> DOUBLE_FILLERS = Set.of(
        new char[] { '0', '0' }, new char[] { '1', '1' },
        new char[] { '6', '9' }, new char[] { '9', '6' }
    );



    List<String> upsideDownNumbers(String start, String end) {
        return null;
    }

    static String createPattern(int start) {
        String raw = String.valueOf(start);
        int len = raw.length() - 2;
        return nearest(raw.charAt(0)) + "x".repeat(len) + '1';
    }

    static char nearest(char input) {
        return switch (input) {
            case '1' -> '1';
            case '2', '3', '4', '5', '6' -> '6';
            case '7', '8' -> '8';
            case '9' -> '9';
            default -> throw new IllegalArgumentException();
        };
    }

    static char increment(char input) {
        return switch (input) {
            case '1', '2', '3', '4', '5' -> '6';
            case '6', '7' -> '8';
            case '8' -> '9';
            case '9' -> '1';
            default -> throw new IllegalArgumentException();
        };
    }

    static Pattern p = Pattern.compile("^\\d+([x])x*([x])\\d+$");

    private Stream<String> upsideDownNumbers(String pattern, int start) {
        //String pattern = createPattern(start, end);
        /*boolean useSingleFillers = (pattern.length() & 1) == 1;
        if(useSingleFillers) {
            int pivot = pattern.length() / 2;
        }*/
        var sb = Stream.<String> builder();
        p.matcher(pattern);
        DOUBLE_FILLERS.forEach(f -> {

        });
        return null;
    }

    public static void main(String[] args) {
        String pa = createPattern(245234654);
        p.matcher("435xxxxx245").replaceAll(m -> "");
    }
}
