package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.WorldView;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

/**
 * Represents gravity in the negative y direction.
 *
 */
final class Gravity implements Force {

    @Override
    public Vector calculate(PhysicalObject object, WorldView world) {
        // F = m g = g m = g / (1/m)
        if (object.invertedMass == 0) {
            // Static objects don't fall
            return world.getZeroVector();
        }
        return world.getZeroVector().withCoord(1, -9.81 / object.invertedMass);
    }

    @Override
    public String toString() {
        return "Forces.GRAVITY";
    }

}
