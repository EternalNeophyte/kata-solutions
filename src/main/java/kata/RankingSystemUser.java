package kata;

import static java.lang.Math.abs;

/**
 * <a href="https://www.codewars.com/kata/51fda2d95d6efda45e00004e/java">Kata details</a>
 */
public final class RankingSystemUser {

    private static final int PROGRESS_CAP = 100;
    public int rank = -8;
    public int progress = 0;

    public void incProgress(int appliedRank) {
        if(appliedRank < -8 || appliedRank == 0 || appliedRank > 8) {
            throw new IllegalArgumentException();
        }
        if(rank == 8) {
            return;
        }
        progress += evaluateImpact(appliedRank);
        while(progress >= PROGRESS_CAP) {
            switch(rank) {
                case 7 -> {
                    rank++;
                    progress = 0;
                }
                case -1 -> {
                    rank = 1;
                    progress -= PROGRESS_CAP;
                }
                default -> {
                    rank++;
                    progress -= PROGRESS_CAP;
                }
            }
        }
    }

    private int evaluateImpact(int appliedRank) {
        int diff = (areOnEitherSides(rank, appliedRank) ||
                    areOnEitherSides(appliedRank, rank))
                ? abs(rank - appliedRank) - 1
                : abs(rank - appliedRank);
        return (rank < appliedRank)
                ? 10 * diff * diff
                : switch(diff) {
                    case 0 -> 3;
                    case 1 -> 1;
                    default -> 0;
                };
    }

    private boolean areOnEitherSides(int rank1, int rank2) {
        return rank1 < 0 && rank2 > 0;
    }
}
