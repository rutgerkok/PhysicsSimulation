package nl.rutgerkok.physicssimulation;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

/**
 * An axis-aligned bounding box. This is a rectangular box used for collision
 * checking.
 *
 */
public final class AxisAlignedBoundingBox implements Drawable {

    private final Vector2 min;
    private final Vector2 max;

    /**
     * Creates a new axis-aligned bounding box.
     * @param min The position of the top-left corner.
     * @param max The position of the bottom-right corner.
     * @return The box.
     */
    public static AxisAlignedBoundingBox aabb(Vector2 min, Vector2 max) {
        if (min.getX() >= max.getX() || min.getY() >= max.getY()) {
            throw new IllegalArgumentException("Invalid dimensions: " + min + " to " + max);
        }
        return new AxisAlignedBoundingBox(min, max);
    }

    private AxisAlignedBoundingBox(Vector2 min, Vector2 max) {
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    /**
     * Returns whether this AABB overlaps with the given AABB.
     * @param that The other AABB.
     * @return True if they overlap, false otherwise.
     */
    public boolean overlapsWith(AxisAlignedBoundingBox that) {
        // Return false when separated along each axis
        if(this.max.getX() <= that.min.getX() || this.min.getX() >= that.max.getX())
            return false;
        if(this.max.getY() <= that.min.getY() || this.min.getY() >= that.max.getY())
            return false;

        // No separating axis found, therefore there is at least one overlapping axis
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
        AxisAlignedBoundingBox other = (AxisAlignedBoundingBox) obj;
        if (!max.equals(other.max))
            return false;
        if (!min.equals(other.min))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AABB from " + this.min + " to " + this.max;
    }

    @Override
    public void toDrawing(Canvas canvas) {
        canvas.drawSquare(this.min, this.max);
    }
}
