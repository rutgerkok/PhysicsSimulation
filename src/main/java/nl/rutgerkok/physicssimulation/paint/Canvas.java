package nl.rutgerkok.physicssimulation.paint;

import nl.rutgerkok.physicssimulation.Vector2;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

/**
 * A canvas that can be drawn on.
 *
 */
public interface Canvas {

    /**
     * Draws a line on this canvas.
     * @param start The start position.
     * @param end The end position.
     */
    void drawLine(Vector2 start, Vector2 end);

    /**
     * Draws a square on the canvas.
     * @param min Top left position.
     * @param max Bottom right position.
     */
    default void drawSquare(Vector2 min, Vector2 max) {
        drawLine(min, vec2(max.getX(), min.getY())); // top
        drawLine(vec2(min.getX(), max.getY()), max); // bottom
        drawLine(min, vec2(min.getX(), max.getY())); // left
        drawLine(vec2(max.getX(), min.getY()), max); // right
    }
}
