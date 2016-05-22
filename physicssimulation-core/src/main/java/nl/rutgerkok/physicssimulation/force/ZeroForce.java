package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

final class ZeroForce implements Force {

    @Override
    public Vector calculate(PhysicalObject object, PhysicsWorld world) {
        // This gets us a vector in the correct dimension
        Vector zero = object.getVelocity().multiply(0);
        return zero;
    }

    @Override
    public String toString() {
        return "Forces.ZERO";
    }

}
