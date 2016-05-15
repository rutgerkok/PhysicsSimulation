package nl.rutgerkok.physicssimulation.paint;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

import nl.rutgerkok.physicssimulation.vector.Vector2;

/**
 * A canvas that can be drawn on.
 *
 */
public interface Canvas {

    /**
     * Draw an arc on this canvas.
     * 
     * @param center
     *            Top left corner of the AABB around the arc.
     * @param size
     *            Size of the arc.
     * @param startAngle
     *            The angle the arc starts drawing, in radians.
     * @param endAngle
     *            The angle the arc stops drawing, relative to the start, in
     *            radians.
     */
    void drawArc(Vector2 center, Vector2 size, double startAngle, double endAngle);

    /**
     * Draws a line on this canvas.
     * 
     * @param start
     *            The start position.
     * @param end
     *            The end position.
     */
    void drawLine(Vector2 start, Vector2 end);

    /**
     * Draws a square on the canvas.
     * 
     * @param min
     *            Top left position.
     * @param max
     *            Bottom right position.
     */
    default void drawSquare(Vector2 min, Vector2 max) {
        drawLine(min, vec2(max.getX(), min.getY())); // top
        drawLine(vec2(min.getX(), max.getY()), max); // bottom
        drawLine(min, vec2(min.getX(), max.getY())); // left
        drawLine(vec2(max.getX(), min.getY()), max); // right
    }
}
