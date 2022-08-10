package kata;


import java.util.stream.DoubleStream;

public final class BouncingBall {

    public static int bouncingBall(double h, double bounce, double window) {
        return satisfied(h, bounce, window) ? timesSeen(h, bounce, window) : -1;
    }

    private static boolean satisfied(double h, double bounce, double window) {
        return h > 0 && bounce > 0 && bounce < 1 && window < h;
    }

    private static int timesSeen(double h, double bounce, double window) {
        int falls = (int) DoubleStream.iterate(h, next -> next >= window, next -> next * bounce).count();
        return falls * 2 - 1;
    }
}
