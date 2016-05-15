package nl.rutgerkok.physicssimulation;

import nl.rutgerkok.physicssimulation.paint.Canvas;

/**
 * Fake drawing canvas that just keeps trac of some statistics.
 *
 */
public class TestCanvas implements Canvas {

    public int lines = 0;
    public int arcs = 0;

    @Override
    public void drawArc(Vector2 center, Vector2 size, double startAngle, double endAngle) {
        arcs++;
    }

    @Override
    public void drawLine(Vector2 start, Vector2 end) {
        lines++;
    }

}
