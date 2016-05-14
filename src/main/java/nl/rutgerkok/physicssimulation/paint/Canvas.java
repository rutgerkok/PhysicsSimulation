package nl.rutgerkok.physicssimulation.paint;

import nl.rutgerkok.physicssimulation.Vector2;

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
        drawLine(min, Vector2.of(max.getX(), min.getY())); // top
        drawLine(Vector2.of(min.getX(), max.getY()), max); // bottom
        drawLine(min, Vector2.of(min.getX(), max.getY())); // left
        drawLine(Vector2.of(max.getX(), min.getY()), max); // right
    }
}
