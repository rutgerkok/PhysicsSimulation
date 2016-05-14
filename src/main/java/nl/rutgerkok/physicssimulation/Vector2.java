package nl.rutgerkok.physicssimulation;

/**
 * A 2-dimensional vector.
 *
 */
public final class Vector2 {

    /**
     * X position of the vector.
     */
    public final double x;

    /**
     * Y position of the vector.
     */
    public final double y;

    /**
     * Creates a vector with the given x and y.
     * @param x The x.
     * @param y The y.
     * @return The vector.
     * @throws IllegalArgumentException If the x or y are infinite or NaN.
     */
    public static Vector2 of(double x, double y) {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("Invalid coords: (" + x + ", " + y + ")");
        }
        return new Vector2(x, y);
    }

    private Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
