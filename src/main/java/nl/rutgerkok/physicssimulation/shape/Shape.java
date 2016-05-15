package nl.rutgerkok.physicssimulation.shape;

import nl.rutgerkok.physicssimulation.Vector;
import nl.rutgerkok.physicssimulation.paint.Drawable;

/**
 * Represents some kind of shape.
 *
 */
public interface Shape extends Drawable {

    /**
     * Gets the center of this shape.
     * 
     * @return The center.
     */
    Vector getCenter();

    /**
     * Gets the volume of this shape. For 2-dimensional shapes, this is equal to
     * the area of the shape.
     *
     * @return The volume.
     */
    double getVolume();

    /**
     * Gets a shape that is equal to this shape, except that the center was
     * moved by the given amount.
     *
     * @param amount
     *            The amount to move.
     * @return The moved object.
     */
    Shape moved(Vector amount);
}
