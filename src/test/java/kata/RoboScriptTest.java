package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoboScriptTest {

    @Test
    public void test() {
        assertDoesNotThrow(() -> RoboScript.highlight("FFF3RF5LF7"));
    }
}
