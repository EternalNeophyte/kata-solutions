package kata;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <a href="https://www.codewars.com/kata/58708934a44cfccca60000c4">Kata details</a>
 */
public final class RoboScript {

    public static String highlight(String code) {
        return Pattern.compile("\\d+|F+|L+|R+|[()]+")
                .matcher(code)
                .results()
                .map(MatchResult::group)
                .map(RoboScript::applyStyle)
                .collect(Collectors.joining());
    }

    private static String applyStyle(String group) {
        return switch (group.charAt(0)) {
            case '(', ')' -> group;
            case 'F' -> span("pink", group);
            case 'L' -> span("red", group);
            case 'R' -> span("green", group);
            default -> span("orange", group);
        };
    }

    private static String span(String color, String content) {
        return "<span style=\"color: %s\">%s</span>".formatted(color, content);
    }
}
