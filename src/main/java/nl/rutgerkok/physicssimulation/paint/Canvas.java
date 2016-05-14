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
}
