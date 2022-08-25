package kata;

/**
 * Created on 25.08.2022
 *
 * @author alexandrov
 */
public class SnakesAndLadders {

    private static final int[] SHORTCUTS;

    static {
        SHORTCUTS = new int[101];
        SHORTCUTS[2] = 38;  SHORTCUTS[7] = 14;  SHORTCUTS[8] = 31;
        SHORTCUTS[15] = 26; SHORTCUTS[16] = 6;  SHORTCUTS[21] = 42;
        SHORTCUTS[28] = 84; SHORTCUTS[36] = 44; SHORTCUTS[46] = 25;
        SHORTCUTS[49] = 11; SHORTCUTS[51] = 67; SHORTCUTS[62] = 19;
        SHORTCUTS[64] = 60; SHORTCUTS[71] = 91; SHORTCUTS[74] = 53;
        SHORTCUTS[78] = 98; SHORTCUTS[87] = 94; SHORTCUTS[89] = 98;
        SHORTCUTS[92] = 88; SHORTCUTS[95] = 75; SHORTCUTS[99] = 80;
    }

    private int square1, square2;
    private boolean player1turn;

    public String play(int die1, int die2) {
        return "";
    }
}
