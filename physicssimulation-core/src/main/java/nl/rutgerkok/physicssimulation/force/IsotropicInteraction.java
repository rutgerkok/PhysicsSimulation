package nl.rutgerkok.physicssimulation.force;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.WorldView;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

/**
 * A simple repulsive force, taking into account the distance between objects.
 *
 */
final class IsotropicInteraction implements Force {

    private final double constant;
    private final int power;

    /**
     * Creates an isotropic interaction force, {@code F = c / r^p}.
     *
     * @param constant
     *            The constant c in the above expression.
     * @param power
     *            The power p in the above expression.
     * @throws IllegalArgumentException
     *             When p is negative or too high, or when c is 0.
     */
    IsotropicInteraction(double constant, int power) {
        if (constant == 0) {
            throw new IllegalArgumentException("Invalid constant: " + constant);
        }
        if (power <= 0 || power > 20) {
            throw new IllegalArgumentException("Invalid power: " + power);
        }
        this.constant = constant;
        this.power = power;
    }

    @Override
    public Vector calculate(PhysicalObject object, WorldView world) {
        Vector resultant = world.getZeroVector();
        for (PhysicalObject otherObject : world) {
            if (otherObject.equals(object)) {
                continue;
            }

            Vector difference = object.getShape().getCenter().minus(otherObject.getShape().getCenter());

            double distance = difference.getLength();
            if (distance == 0) {
                // Prevent division by zero
                continue;
            }
            Vector differenceNormalized = difference.divide(distance);
            resultant = resultant.plus(differenceNormalized.multiply(constant / Math.pow(distance, power)));
        }
        return resultant;
    }

    @Override
    public String toString() {
        if (constant < 0) {
            return "Forces.attraction(" + -constant + ", " + power + ")";
        } else {
            return "Forces.repulsion(" + constant + ", " + power + ")";
        }
    }

}
