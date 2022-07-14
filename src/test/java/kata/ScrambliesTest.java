package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScrambliesTest {

    @Test
    public void test() {
        assertTrue(Scramblies.scramble("rkqodlw", "world"));
        assertTrue(Scramblies.scramble("cedewaraaossoqqyt","codewars"));
        assertFalse(Scramblies.scramble("scriptjavx","javascript"));
    }
}
