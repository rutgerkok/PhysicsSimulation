package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.WorldView;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

final class ZeroForce implements Force {

    @Override
    public Vector calculate(PhysicalObject object, WorldView world) {
        return world.getZeroVector();
    }

    @Override
    public String toString() {
        return "Forces.ZERO";
    }

}
