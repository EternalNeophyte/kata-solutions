package kata;

import org.junit.jupiter.api.Test;

import static kata.NextBiggerNumber.nextBiggerNumber;
import static org.junit.jupiter.api.Assertions.*;

public class NextBiggerNumberTest {

    @Test
    public void test() {
        assertEquals(21, nextBiggerNumber(12));
        assertEquals(531, nextBiggerNumber(513));
        assertEquals(2071, nextBiggerNumber(2017));
        assertEquals(441, nextBiggerNumber(414));
        assertEquals(414, nextBiggerNumber(144));
        assertEquals(19009, nextBiggerNumber(10990));
        assertEquals(2098347488, nextBiggerNumber(2098344788));
    }

    //28118 <- 21881

}
