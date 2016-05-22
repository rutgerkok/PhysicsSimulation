package nl.rutgerkok.physicssimulation.vector;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

import org.eclipse.jdt.annotation.Nullable;

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
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Vector2 minus(Vector other) {
        Vector2 that = asVec2(other);
        return vec2(this.x - that.x, this.y - that.y);
    }

    @Override
    public Vector2 multiply(double scalar) {
        return vec2(this.x * scalar, this.y * scalar);
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

    @Override
    public Vector withCoord(int position, double value) {
        switch (position) {
            case 0:
                return vec2(value, y);
            case 1:
                return vec2(x, value);
            default:
                throw new IllegalArgumentException("Invalid position for " + this + ": " + position);
        }
    }

}
