package nl.rutgerkok.physicssimulation.force;

import java.util.Collection;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;

/**
 * Holds all the built-in forces.
 *
 */
public final class Forces {
    public static final Force GRAVITY = new Gravity();
    public static final Force ZERO = new ZeroForce();

    /**
     * Creates an isotropic (=non-directional) attractive force,
     * {@code -F = c / r^p}.
     *
     * @param constant
     *            The constant c in the above expression.
     * @param power
     *            The power p in the above expression.
     * @return The repulsive force.
     * @throws IllegalArgumentException
     *             When c or p are not positive.
     * @throws IllegalArgumentException
     *             When p is too high.
     * @see #repulsion(double, int) A repulsive force
     */
    public static Force attraction(double constant, int power) {
        if (constant < 0) {
            throw new IllegalArgumentException("Invalid constant: " + constant
                    + " (maybe use a repulsive force instead?)");
        }
        return new IsotropicInteraction(-constant, power);
    }

    /**
     * Combines the given forces into one resultant force.
     * 
     * @param forces
     *            The forces.
     * @return The resultant force.
     */
    public static Force combine(Collection<Force> forces) {
        Force[] immutableForces = forces.toArray(new Force[0]);
        return (object, world) -> {
            Vector result = world.getZeroVector();

            for (Force force : immutableForces) {
                result = result.plus(force.calculate(object, world));
            }

            return result;
        };
    }

    /**
     * Creates a
     * <a href="https://en.wikipedia.org/wiki/Lennard-Jones_potential">Lennard-
     * Jones potential</a>.
     *
     * @param epsilon
     *            Depth of potential well.
     * @param sigma
     *            Distance between particle centers where potential energy is 0.
     * @return The Lennard-Jones potential.
     */
    public static Force lennardJones(double epsilon, double sigma) {
        Force repulsive = Forces.repulsion(4 * epsilon * Math.pow(sigma, 12), 12);
        Force attractive = Forces.attraction(4 * epsilon * Math.pow(sigma, 6), 6);
        return (object, world) -> repulsive.calculate(object, world).plus(attractive.calculate(object, world));
    }

    /**
     * Creates an isotropic (=non-directional) repulsive force,
     * {@code F = c / r^p}.
     *
     * @param constant
     *            The constant c in the above expression.
     * @param power
     *            The power p in the above expression.
     * @return The repulsive force.
     * @throws IllegalArgumentException
     *             When c or p are not positive.
     * @throws IllegalArgumentException
     *             When p is too high.
     * @see #attraction(double, int) An attractive force.
     */
    public static Force repulsion(double constant, int power) {
        if (constant < 0) {
            throw new IllegalArgumentException("Invalid constant: " + constant
                    + " (maybe use an attractive force instead?)");
        }
        return new IsotropicInteraction(constant, power);
    }

    Forces() {
        throw new RuntimeException("No instances");
    }
}
