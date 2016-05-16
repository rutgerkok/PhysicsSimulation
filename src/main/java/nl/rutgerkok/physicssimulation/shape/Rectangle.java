package nl.rutgerkok.physicssimulation.shape;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;

import org.eclipse.jdt.annotation.Nullable;

/**
 * An axis-aligned bounding box. This is a rectangular box used for collision
 * checking.
 *
 */
public final class Rectangle implements Shape {

    /**
     * Creates a new axis-aligned bounding box.
     * 
     * @param min
     *            The position of the top-left corner.
     * @param max
     *            The position of the bottom-right corner.
     * @return The box.
     */
    public static Rectangle rectangle(Vector2 min, Vector2 max) {
        if (min.getX() >= max.getX() || min.getY() >= max.getY()) {
            throw new IllegalArgumentException("Invalid dimensions: " + min + " to " + max);
        }
        return new Rectangle(min, max);
    }

    private final Vector2 min;
    private final Vector2 max;

    private Rectangle(Vector2 min, Vector2 max) {
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Rectangle))
            return false;
        Rectangle other = (Rectangle) obj;
        if (!max.equals(other.max))
            return false;
        if (!min.equals(other.min))
            return false;
        return true;
    }

    @Override
    public Vector2 getCenter() {
        // Return average of min and max
        return min.plus(max).divide(2);
    }

    /**
     * Gets the position with the highest x and y that is still contained within
     * the rectangle.
     * 
     * @return The maximum position.
     */
    public Vector2 getMax() {
        return max;
    }

    /**
     * Gets the position with the lowest x and y that is still contained within
     * the rectangle.
     * 
     * @return The minimum position.
     */
    public Vector2 getMin() {
        return min;
    }

    @Override
    public double getVolume() {
        double xSize = this.max.getX() - this.min.getX();
        double ySize = this.max.getY() - this.min.getY();
        return xSize * ySize;
    }

    /**
     * Gets the size of this rectangle in the x direction.
     * 
     * @return The size.
     */
    public double getXSize() {
        return this.max.getX() - this.min.getX();
    }

    /**
     * Gets the size of this rectangle in the y direction.
     * 
     * @return The size.
     */
    public double getYSize() {
        return this.max.getY() - this.min.getY();
    }

    @Override
    public int hashCode() {
        return min.hashCode() ^ max.hashCode();
    }

    @Override
    public Shape moved(Vector amount) {
        return rectangle(min.plus(amount), max.plus(amount));
    }

    @Override
    public void toDrawing(Canvas canvas) {
        canvas.drawRectangle(this.min, this.max);
    }

    @Override
    public String toString() {
        return "rectangle(" + this.min + ", " + this.max + ")";
    }
}
