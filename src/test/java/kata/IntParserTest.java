package kata;

import org.junit.jupiter.api.Test;

import static kata.IntParser.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntParserTest {

    @Test
    public void test() {
        assertEquals(101, parseInt("one-hundred and one"));
        assertEquals(101, parseInt("hundred and one"));
        assertEquals(783919, parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
        assertEquals(700000, parseInt("seven hundred thousand"));
    }
}
