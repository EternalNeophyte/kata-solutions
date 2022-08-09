package kata;

import org.junit.jupiter.api.Test;

import static kata.PigLatinAndOrder.order;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class PigLatinAndOrderTest {

    @Test
    public void test1() {
        assertThat(order("is2 Thi1s T4est 3a"), equalTo("Thi1s is2 3a T4est"));
    }

    @Test
    public void test2() {
        assertThat(order("4of Fo1r pe6ople g3ood th5e the2"), equalTo("Fo1r the2 g3ood 4of th5e pe6ople"));
    }

    @Test
    public void test3() {
        assertThat("Empty input should return empty string", order(""), equalTo(""));
    }
}
