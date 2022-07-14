package kata;

import java.util.TreeMap;
import java.util.stream.Stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * <a href="https://www.codewars.com/kata/55c04b4cc56a697bb0000048/train/java">Kata details</a>
 */
public final class Scramblies {

    public static boolean scramble(String str1, String str2) {
        var patternBuilder = new StringBuilder();
        var countedSymbols = symbolsOf(str2)
                .collect(groupingBy(identity(), TreeMap::new, counting()));
        countedSymbols.forEach((symbol, count) -> patternBuilder.append("[")
                .append(symbol).append("]{")
                .append(count).append(",}"));
        //  Goes to regex like "aica" => "[a]{2,}[c]{1,}[i]{1,}"
        String pattern = patternBuilder.toString();
        String sample = symbolsOf(str1)
                .filter(countedSymbols::containsKey)
                .sorted()
                .collect(joining());
        return sample.matches(pattern);
    }

    private static Stream<String> symbolsOf(String source) {
        return source.chars().mapToObj(Character::toString);
    }
}
