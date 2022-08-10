package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleOfPrimesTest {

    @Test
    public void testSmall() {
        assertEquals("23", new MultipleOfPrimes().findThem("10", new int[]{3,5}));
        assertEquals("37", new MultipleOfPrimes().findThem("10", new int[]{2,3,5}));
    }

    @Test
    public void testMedium() {
        assertEquals("4209783663", new MultipleOfPrimes().findThem("100000", new int[]{2,3,5,7,11,13,17,19,23,29}));
    }

    @Test
    public void testBig() {
        assertEquals("4256395812485266", new MultipleOfPrimes().findThem("100000001", new int[]{2,3,5,7,11,13,17,19,23,29,31,37}));
    }
}
