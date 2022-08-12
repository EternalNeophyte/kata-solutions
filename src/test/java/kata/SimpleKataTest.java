package kata;

import org.junit.jupiter.api.Test;

import static kata.SimpleKatas.encode;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 11.08.2022
 *
 * @author alexandrov
 */
public class SimpleKataTest {

    @Test
    public void encoderTest() {
        assertEquals(")()())()(()()(",
                encode("Prespecialized"));
        assertEquals("))))())))", encode("   ()(   "));
    }
}
