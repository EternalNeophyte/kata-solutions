package kata;

import static java.lang.Math.abs;

/**
 * <a href="https://www.codewars.com/kata/51fda2d95d6efda45e00004e/java">Kata details</a>
 */
public final class RankingSystemUser {

    private static final int PROGRESS_CAP = 100;
    private int rank = -8;
    private int progress = 0;

    public void incProgress(int rank) {
        if(rank < -8 || rank == 0 || rank > 8) {
            throw new IllegalArgumentException();
        }
        int diff = abs(this.rank - rank) + 1;
        int impact = 10 * diff * diff;
        progress += impact;
        while(progress >= PROGRESS_CAP && this.rank < 8) {
            progress -= PROGRESS_CAP;
            if(this.rank == -1)
                this.rank = 1;
            else
                this.rank++;
        }
    }

    public int getRank() {
        return rank;
    }
}
