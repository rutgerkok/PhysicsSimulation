package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.world.Force;

/**
 * Holds all the built-in forces.
 *
 */
public final class Forces {
    public static final Force GRAVITY = new Gravity();
    public static final Force ZERO = new ZeroForce();
}
