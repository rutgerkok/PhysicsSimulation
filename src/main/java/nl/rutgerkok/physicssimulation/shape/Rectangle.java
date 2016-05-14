package nl.rutgerkok.physicssimulation.shape;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.paint.Canvas;

/**
 * An axis-aligned bounding box. This is a rectangular box used for collision
 * checking.
 *
 */
public final class Rectangle implements Shape {

    private final Vector2 min;
    private final Vector2 max;

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

    private Rectangle(Vector2 min, Vector2 max) {
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    /**
     * Returns whether this AABB overlaps with the given AABB.
     * 
     * @param that
     *            The other AABB.
     * @return True if they overlap, false otherwise.
     */
    public boolean overlapsWith(Rectangle that) {
        // Return false when separated along each axis
        if (this.max.getX() <= that.min.getX() || this.min.getX() >= that.max.getX())
            return false;
        if (this.max.getY() <= that.min.getY() || this.min.getY() >= that.max.getY())
            return false;

        // No separating axis found, therefore there is at least one overlapping
        // axis
        return true;

    }

    @Override
    public int hashCode() {
        return min.hashCode() ^ max.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rectangle other = (Rectangle) obj;
        if (!max.equals(other.max))
            return false;
        if (!min.equals(other.min))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "aabb(" + this.min + ", " + this.max + ")";
    }

    @Override
    public void toDrawing(Canvas canvas) {
        canvas.drawSquare(this.min, this.max);
    }

    @Override
    public double getArea() {
        double xSize = this.max.getX() - this.min.getX();
        double ySize = this.max.getY() - this.min.getY();
        return xSize * ySize;
    }

    @Override
    public Vector2 getCenter() {
        // Return average of min and max
        return min.plus(max).divide(2);
    }

    @Override
    public Shape moved(Vector2 amount) {
        return rectangle(min.plus(amount), max.plus(amount));
    }
}
