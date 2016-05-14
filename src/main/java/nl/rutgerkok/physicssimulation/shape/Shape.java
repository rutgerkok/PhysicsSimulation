package nl.rutgerkok.physicssimulation.shape;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.paint.Drawable;

/**
 * Represents some kind of shape.
 *
 */
public interface Shape extends Drawable {

    /**
     * Gets the area of this shape.
     *
     * @return The area.
     */
    double getArea();

    /**
     * Gets the center of this shape.
     * 
     * @return The center.
     */
    Vector2 getCenter();

    /**
     * Gets a shape that is equal to this shape, except that the center was
     * moved by the given amount.
     *
     * @param amount
     *            The amount to move.
     * @return The moved object.
     */
    Shape moved(Vector2 amount);
}
