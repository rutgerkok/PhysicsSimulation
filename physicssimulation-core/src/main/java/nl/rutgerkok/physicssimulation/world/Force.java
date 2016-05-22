package nl.rutgerkok.physicssimulation.world;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

/**
 * Represents some physical force (as in {@code F = m a}).
 *
 */
public interface Force {

    /**
     * Calculates the force that should be applied to the given object.
     * 
     * @param object
     *            The object.
     * @param world
     *            The world.
     * @return The force.
     */
    Vector calculate(PhysicalObject object, PhysicsWorld world);

}
