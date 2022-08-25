package kata;

/**
 * Created on 25.08.2022
 *
 * @author alexandrov
 */
public class SnakesAndLadders {

    public static final int FINISH = 100;
    public static final int[] SHORTCUTS;

    static {
        SHORTCUTS = new int[101];
        SHORTCUTS[2] = 38;  SHORTCUTS[7] = 14;  SHORTCUTS[8] = 31;
        SHORTCUTS[15] = 26; SHORTCUTS[16] = 6;  SHORTCUTS[21] = 42;
        SHORTCUTS[28] = 84; SHORTCUTS[36] = 44; SHORTCUTS[46] = 25;
        SHORTCUTS[49] = 11; SHORTCUTS[51] = 67; SHORTCUTS[62] = 19;
        SHORTCUTS[64] = 60; SHORTCUTS[71] = 91; SHORTCUTS[74] = 53;
        SHORTCUTS[78] = 98; SHORTCUTS[87] = 94; SHORTCUTS[89] = 68;
        SHORTCUTS[92] = 88; SHORTCUTS[95] = 75; SHORTCUTS[99] = 80;
    }

    private Player player;
    private boolean gameOver;

    public SnakesAndLadders() {
        player = new Player(null, "Player 1", 0);
        player.next = new Player(player, "Player 2", 0);
        gameOver = false;
    }

    public String play(int die1, int die2) {
        if(gameOver)
            return "Game over!";
        String playerName = player.name;
        int newPosition = player.position + die1 + die2;
        if(newPosition == FINISH) {
            gameOver = true;
            return playerName + " Wins!";
        }
        if(newPosition > FINISH)
            newPosition = FINISH - (newPosition - FINISH);
        if(SHORTCUTS[newPosition] != 0)
            newPosition = SHORTCUTS[newPosition];
        player.position = newPosition;
        if(die1 != die2)
            player = player.next;
        return playerName + " is on square " + newPosition;
    }

    public static class Player {

        private Player next;
        private final String name;
        private int position;

        public Player(Player next, String name, int position) {
            this.next = next;
            this.name = name;
            this.position = position;
        }
    }
}
