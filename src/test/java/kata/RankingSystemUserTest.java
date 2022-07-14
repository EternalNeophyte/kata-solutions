package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankingSystemUserTest {

    @Test
    public void test() {
        RankingSystemUser user = new RankingSystemUser();
        assertEquals(-8, user.getRank());
        user.incProgress(-7);
        assertEquals(-8, user.getRank());
        user.incProgress(-5);
        assertEquals(-6, user.getRank());
    }
}
