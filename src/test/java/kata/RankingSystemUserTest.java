package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankingSystemUserTest {

    @Test
    public void test() {
        RankingSystemUser user = new RankingSystemUser();
        assertEquals(-8, user.rank);
        user.incProgress(-6);
        assertEquals(-8, user.rank);
        user.incProgress(-4);
        assertEquals(-6, user.rank);
        user.rank = 7;
        for (int i = 0; i < 15; i++) user.incProgress(8);
        assertEquals(0, user.progress);
        user.rank = 7;
        user.progress = 99;
        user.incProgress(8);
        assertEquals(8, user.rank);
        assertEquals(0, user.progress);
        user.incProgress(8);
        assertEquals(8, user.rank);
        assertEquals(0, user.progress);
    }
}
