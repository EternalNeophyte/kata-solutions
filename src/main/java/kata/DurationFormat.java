package kata;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DurationFormat {

    public static String formatDuration(int seconds) {
        if(seconds == 0)
            return "now";

        var dateTime = LocalDateTime.of(0, Month.JANUARY,1,0,0,0).plusSeconds(seconds);
        int year = dateTime.getYear();
        // Leap years are not counted in this kata, so...
        dateTime = dateTime.minusDays(year / 4);
        return Stream.of(
                new Unit("year", year),
                new Unit("day", dateTime.getDayOfYear() - 1),
                new Unit("hour", dateTime.getHour()),
                new Unit("minute", dateTime.getMinute()),
                new Unit("second", dateTime.getSecond())
            )
            .filter(u -> u.value > 0)
            .map(Unit::toString)
            .collect(Collectors.joining(", "))
            .replaceFirst(", (\\d+ \\w+)$", " and $1");
    }

    private static record Unit(String name, int value) {

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(value).append(' ').append(name);
            if(value != 1)
                sb.append('s');
            return sb.toString();
        }
    }

}
