package kata;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.round;
import static java.util.Comparator.comparingInt;

/**
 * <a href="https://www.codewars.com/kata/537e18b6147aa838f600001b/java">Kata details</a>
 */
public final class TextAlignJustify {

    public static final String DELIMITER = Character.toString('\u0000'), SPACE = " ";

    public static String justify(String text, int width) {
        StringJoiner justifiedContent = new StringJoiner("\n");
        StringBuilder lineBuilder = new StringBuilder();
        int wordsInLine = 0;
        for(String word : text.split("\\s+")) {
            if(lineBuilder.length() + word.length() > width) {
                int spacesToAdd = width - lineBuilder.length() + wordsInLine;
                String line = splitToGaps(spacesToAdd, wordsInLine)
                                .reduce(lineBuilder.toString().trim(),
                                        (result, gap) -> result.replaceFirst(DELIMITER, gap));
                justifiedContent.add(line);
                lineBuilder.delete(0, lineBuilder.length());
                wordsInLine = 0;
            }
            lineBuilder.append(word).append(DELIMITER);
            wordsInLine++;
        }
        return justifiedContent.add(lineBuilder.toString().replaceAll(DELIMITER, SPACE))
                .toString()
                .trim();
    }

    private static Stream<String> splitToGaps(double sum, int count) {
        AtomicInteger accumulated = new AtomicInteger();
        double factor = sum / (count - 1);
        return IntStream.range(1, count)
                .map(i -> {
                    int rounded = (int) round(factor * i);
                    return rounded - accumulated.getAndSet(rounded);
                })
                .mapToObj(SPACE::repeat)
                .sorted(comparingInt(String::length).reversed());
    }

}
