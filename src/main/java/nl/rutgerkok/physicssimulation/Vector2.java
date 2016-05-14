package nl.rutgerkok.physicssimulation;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

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
    public static Vector2 of(double x, double y) {
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

    @Override
    public boolean equals(Object obj) {
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
     * Adds another vector to this vector.
     * 
     * @param that
     *            The other vector.
     * @return The result vector.
     */
    public Vector2 plus(Vector2 that) {
        return Vector2.of(this.x + that.x, this.y + that.y);
    }
    
    /**
     * Adds another vector to this vector.
     * @param x X position of the other vector.
     * @param y Y position of the other vector.
     * @return The other vector.
     */
    public Vector2 plus(double x, double y) {
        return Vector2.of(this.x + x, this.y + y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public void toDrawing(Canvas canvas) {
        // Draw a cross
        canvas.drawLine(this.plus(-2, -2), this.plus(2, 2));
        canvas.drawLine(this.plus(2, -2), this.plus(-2, 2));
    }

}
