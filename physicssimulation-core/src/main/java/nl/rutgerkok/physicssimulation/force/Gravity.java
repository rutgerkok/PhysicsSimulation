package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

/**
 * Represents gravity in the negative y direction.
 *
 */
final class Gravity implements Force {

    @Override
    public Vector calculate(PhysicalObject object, PhysicsWorld world) {
        // F = m g = g m = g / (1/m)
        Vector zero = object.getVelocity().multiply(0);
        return zero.withCoord(1, -9.81 / object.invertedMass);
    }

    @Override
    public String toString() {
        return "Forces.GRAVITY";
    }

}
