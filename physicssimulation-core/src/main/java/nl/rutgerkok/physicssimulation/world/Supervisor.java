package nl.rutgerkok.physicssimulation.world;

import nl.rutgerkok.physicssimulation.vector.Vector;

/**
 * Checks the positions of all objects after a simulation step, to make sure no
 * impossible things can happen.
 */
public interface Supervisor
{
    /**
     * Checks the world for impossible situations, like overlapping objects.
     * @param world The world.
     */
    void check(PhysicsSimulation world);

    /**
     * Changes the velocity of the given object.
     * @param object The object.
     * @param velocity The new velocity.
     */
    default void changeVelocity(PhysicalObject object, Vector velocity) {
        object.replaceVelocity(velocity);
    }
}
