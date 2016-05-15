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
    public void drawArc(Vector center, Vector size, double startAngle, double endAngle) {
        arcs++;
    }

    @Override
    public void drawLine(Vector start, Vector end) {
        lines++;
    }

}
