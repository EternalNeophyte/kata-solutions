package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RomanNumeralsTest {

    @Test
    public void test() {
        assertEquals(1990, RomanNumerals.fromRoman("MCMXC"));
        assertEquals(2008, RomanNumerals.fromRoman("MMVIII"));
        assertEquals(1, RomanNumerals.fromRoman("I"));
        assertEquals(2, RomanNumerals.fromRoman("II"));
        assertEquals("IV", RomanNumerals.toRoman(4));
        assertEquals("MMMCDII", RomanNumerals.toRoman(3402));
        assertEquals("IX", RomanNumerals.toRoman(9));
        assertEquals("MMVIII", RomanNumerals.toRoman(2008));
        assertEquals("MCMXC", RomanNumerals.toRoman(1990));
    }
}
