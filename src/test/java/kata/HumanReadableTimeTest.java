package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanReadableTimeTest {

    @Test
    public void test() {
        assertThrows(AssertionError.class, () -> HumanReadableTime.makeReadable(600_000));
        assertEquals("99:59:59", HumanReadableTime.makeReadable(359999));
        assertEquals("00:00:00", HumanReadableTime.makeReadable(0));
    }
}
