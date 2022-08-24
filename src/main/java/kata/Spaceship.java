package kata;

import static java.lang.Math.*;

/**
 * Created on 24.08.2022
 *
 * @author alexandrov
 */
public class Spaceship {

    static final double JUMP_COST = 2.5, ENERGY_LOSS_PER_LY = 0.625;

    private double charge;

    record StellarObject(Type type, int x, int y, int z) { }

    enum Type {
        STAR, NEUTRON_STAR, BLACK_HOLE;
    }

    static double displayJumpCost(StellarObject o1, StellarObject o2) {
        return JUMP_COST + ENERGY_LOSS_PER_LY * distanceBetween(o1, o2);
    }

    static double distanceBetween(StellarObject o1, StellarObject o2) {
        return sqrt(pow(o1.x - o2.x, 2) + pow(o1.y - o2.y, 2) + pow(o1.z - o2.z, 2));
    }


}
