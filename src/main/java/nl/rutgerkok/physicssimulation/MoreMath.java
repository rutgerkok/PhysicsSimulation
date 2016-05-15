package nl.rutgerkok.physicssimulation;

public final class MoreMath {

    /**
     * Clamps a value between a minimum and a maximum. So
     * {@code clamp(-3, x, 2)} will always return a value between -3 and 2,
     * inclusive.
     *
     * @param min
     *            The minimum.
     * @param max
     *            The maximum.
     * @param value
     *            The value.
     * @return The clamped value.
     */
    public static double clamp(double min, double max, double value) {
        if (min > max) {
            throw new IllegalArgumentException("min > max: " + min + " > " + max);
        }
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

}
