package nl.rutgerkok.physicssimulation;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A 2-dimensional immutable vector.
 *
 */
public final class Vector2 implements Drawable {

    /**
     * Creates a vector with the given x and y.
     * 
     * @param x
     *            The x.
     * @param y
     *            The y.
     * @return The vector.
     * @throws IllegalArgumentException
     *             If the x or y are infinite or NaN.
     */
    public static Vector2 vec2(double x, double y) {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("Invalid coords: (" + x + ", " + y + ")");
        }
        return new Vector2(x, y);
    }

    /**
     * X position of the vector.
     */
    private final double x;

    /**
     * Y position of the vector.
     */
    private final double y;

    private Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Divides this vector with a scalar.
     *
     * @param scalar
     *            The scalar.
     * @return The divided vector.
     * @throws IllegalArgumentException
     *             On division by 0.
     */
    public Vector2 divide(double scalar) {
        return multiply(1 / scalar);
    }

    /**
     * Gets the dot product between this vector and the other.
     * 
     * @param that
     *            The other vector.
     * @return The dot product.
     */
    public double dotProduct(Vector2 that) {
        return this.x * that.x + this.y * that.y;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector2))
            return false;
        Vector2 other = (Vector2) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

    /**
     * Calculates the distance to the other vector.
     *
     * @param that
     *            The other vector.
     * @return The distance.
     */
    public double getDistanceTo(Vector2 that) {
        return Math.sqrt(getSquaredDistanceTo(that));
    }

    /**
     * Gets the length of this vector.
     * 
     * @return The length.
     * @see #getSquaredLength() Faster method
     */
    public double getLength() {
        return Math.sqrt(this.dotProduct(this));
    }

    /**
     * Calculates the squared distance to the other vector. Calculating the
     * squared distance is faster than calculating the distance, as we don't
     * have to calculate a square root.
     *
     * @param that
     *            The other vector.
     * @return The distance.
     */
    public double getSquaredDistanceTo(Vector2 that) {
        double xDistance = this.x - that.x;
        double yDistance = this.y - that.y;
        return xDistance * xDistance + yDistance * yDistance;
    }

    /**
     * Gets the squared length of this vector. Unlike {@link #getLength()}, this
     * method doesn't need to calculate a costly square root.
     * 
     * @return The squared length.
     */
    public double getSquaredLength() {
        return this.dotProduct(this);
    }

    /**
     * Gets the x position of this vector.
     * 
     * @return The x position.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y position of this vector.
     * 
     * @return The y position.
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

    /**
     * Subtracts another vector from this vector, and returns the result.
     *
     * @param that
     *            The other vector.
     * @return The result.
     */
    public Vector2 minus(Vector2 that) {
        return vec2(this.x - that.x, this.y - that.y);
    }

    /**
     * Multiplies this vector with a scalar.
     * 
     * @param scalar
     *            The scalar.
     * @return The multiplied vector.
     */
    public Vector2 multiply(double scalar) {
        return vec2(this.x * scalar, this.y * scalar);
    }

    /**
     * Gets a normalized version of this vector, i.e. a vector with the same
     * direction, but a different magnitude.
     * 
     * @return The normalized version.
     */
    public Vector2 normalized() {
        return this.divide(this.getLength());
    }

    /**
     * Adds another vector to this vector.
     * 
     * @param that
     *            The other vector.
     * @return The result vector.
     */
    public Vector2 plus(Vector2 that) {
        return vec2(this.x + that.x, this.y + that.y);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        // Draw a cross
        canvas.drawLine(this.plus(vec2(-2, -2)), this.plus(vec2(2, 2)));
        canvas.drawLine(this.plus(vec2(2, -2)), this.plus(vec2(-2, 2)));
    }

    @Override
    public String toString() {
        double roundedX = Math.round(x * 100) / 100;
        double roundedY = Math.round(y * 100) / 100;
        return "vec2(" + roundedX + ", " + roundedY + ")";
    }

}
