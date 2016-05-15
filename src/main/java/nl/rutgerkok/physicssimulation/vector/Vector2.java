package nl.rutgerkok.physicssimulation.vector;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

/**
 * Represents a two-dimensional vector.
 *
 */
public final class Vector2 implements Vector {

    private final double x;
    private final double y;

    Vector2(double x, double y) {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("Invalid coords: (" + x + ", " + y + ")");
        }
        this.x = x;
        this.y = y;
    }

    private Vector2 asVec2(Vector that) {
        if (that instanceof Vector2) {
            return (Vector2) that;
        }
        throw new IllegalArgumentException("Cannot use vector of another dimension. " + this + " vs " + that);
    }

    @Override
    public Vector2 divide(double scalar) {
        return vec2(this.x / scalar, this.y / scalar);
    }

    @Override
    public double getCoord(int i) {
        if (i == 0) {
            return x;
        }
        if (i == 1) {
            return y;
        }
        throw new IllegalArgumentException("Invalid coord position: " + i);
    }

    @Override
    public int getDimension() {
        return 2;
    }

    /**
     * Gets the x coord of this vector.
     * 
     * @return The x coord.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coord of this vector.
     * 
     * @return The y coord.
     */
    public double getY() {
        return y;
    }

    @Override
    public Vector2 minus(Vector other) {
        Vector2 that = asVec2(other);
        return vec2(this.x - that.x, this.y - that.y);
    }

    @Override
    public Vector2 plus(Vector other) {
        Vector2 that = asVec2(other);
        return vec2(this.x + that.x, this.y + that.y);
    }

    @Override
    public String toString() {
        return "vec2(" + x + ", " + y + ")";
    }

}
