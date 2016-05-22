package nl.rutgerkok.physicssimulation.paint;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.vector.Vector3;

/**
 * A canvas that can be drawn on.
 *
 */
public interface Canvas {

    /**
     * Draw an egg (elongated sphere) on this canvas.
     * 
     * @param min
     *            Top left corner of the AABB around the egg.
     * @param end
     *            Bottom right corner of the AABB around the egg.
     */
    void drawEgg(Vector3 min, Vector3 end);

    /**
     * Draw an ellipse on this canvas.
     * 
     * @param start
     *            Top left corner of the AABB around the ellipse.
     * @param end
     *            Bottom right corner of the AABB around the ellipse.
     */
    void drawEllips(Vector2 start, Vector2 end);

    /**
     * Draws a line on this canvas.
     * 
     * @param start
     *            The start position.
     * @param end
     *            The end position.
     * @throws IllegalArgumentException
     *             If start and end are in different dimensions.
     */
    void drawLine(Vector start, Vector end);

    /**
     * Draws a square on the canvas.
     *
     * @param min
     *            Top left position.
     * @param max
     *            Bottom right position.
     */
    default void drawRectangle(Vector2 min, Vector2 max) {
        drawLine(min, vec2(max.getX(), min.getY())); // top
        drawLine(vec2(min.getX(), max.getY()), max); // bottom
        drawLine(min, vec2(min.getX(), max.getY())); // left
        drawLine(vec2(max.getX(), min.getY()), max); // right
    }
}
