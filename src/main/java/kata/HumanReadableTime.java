package kata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * <a href="https://www.codewars.com/kata/52685f7382004e774f0001f7/java">Kata details</a>
 */
public final class HumanReadableTime {

    public static final LocalDateTime EPOCH = LocalDateTime.of(LocalDate.EPOCH, LocalTime.MIN);

    public static String makeReadable(int seconds) {
        assert (seconds >= 0 && seconds < 360_000) : "Time should not exceed the range";
        LocalDateTime time = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
        long hours = ChronoUnit.HOURS.between(EPOCH, time);
        return "%02d:%02d:%02d".formatted(hours, time.getMinute(), time.getSecond());
    }
}
