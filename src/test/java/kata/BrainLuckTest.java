package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrainLuckTest {

    @Test
    public void test() {
        var loops = BrainLuck.buildLoops(",>,<[>[->+>+<<]>>[-<<+>>]<<<-]>>.".toCharArray());
        loops.toString();
    }
}
