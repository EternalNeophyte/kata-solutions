package kata;

import org.junit.jupiter.api.Test;

import static kata.MissingLetter.findMissingLetter;
import static org.junit.jupiter.api.Assertions.*;

public class MissingLetterTest {

    @Test
    public void test() {
        assertEquals('e', findMissingLetter(new char[] { 'a','b','c','d','f' }));
        assertEquals('P', findMissingLetter(new char[] { 'O','Q','R','S' }));
    }

}
