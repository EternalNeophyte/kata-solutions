package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 14.10.2022
 *
 * @author alexandrov
 */
public class DurationFormatTest {

    @Test
    public void exampleTests() {
        assertEquals("1 second", DurationFormat.formatDuration(1));
        assertEquals("1 minute and 2 seconds", DurationFormat.formatDuration(62));
        assertEquals("2 minutes", DurationFormat.formatDuration(120));
        assertEquals("1 hour", DurationFormat.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", DurationFormat.formatDuration(3662));
    }
}
