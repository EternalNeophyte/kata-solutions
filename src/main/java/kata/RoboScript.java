package kata;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static kata.RoboScript.Direction.RIGHT;

/**
 * <a href="https://www.codewars.com/kata/58708934a44cfccca60000c4">Kata details</a>
 */
public final class RoboScript {

    public static final Pattern COMMAND_PATTERN = Pattern.compile("[FLR][\\d]*");

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

    public static String execute(String code) {
        Direction current = RIGHT;
        List<Point> path = COMMAND_PATTERN
                .matcher(code)
                .results()
                .map(MatchResult::group)
                .map(group -> {
                    if(group.length() > 1) {

                    }
                    return new Point(0, 0);
                })
                .toList();

        return null;
    }

    enum Direction {
        UP((point, i) -> new Point(point.x, point.y + i)),
        RIGHT((point, i) -> new Point(point.x + i, point.y)),
        DOWN((point, i) -> new Point(point.x, point.y - i)),
        LEFT((point, i) -> new Point(point.x - i, point.y));

        final BiFunction<Point, Integer, Point> move;

        Direction(BiFunction<Point, Integer, Point> move) {
            this.move = move;
        }

        static Direction of(char literal) {
            return switch (literal) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                default -> UP;
            };
        }

        Direction change(Direction turn) {
            return switch (this) {
                case UP -> selectBy(turn, RIGHT, LEFT);
                case RIGHT -> selectBy(turn, DOWN, UP);
                case DOWN -> selectBy(turn, LEFT, RIGHT);
                case LEFT -> selectBy(turn, UP, DOWN);
            };
        }

        private Direction selectBy(Direction turn, Direction rightOption, Direction leftOption) {
            return switch (turn) {
                case RIGHT -> rightOption;
                case LEFT -> leftOption;
                default -> this;
            };
        }
    }

    static record Point(int x, int y) { }
}
