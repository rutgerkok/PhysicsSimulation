package nl.rutgerkok.physicssimulation.paint;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.vector.Vector3;

/**
 * Fake drawing canvas that just keeps trac of some statistics.
 *
 */
public class CountingCanvas implements Canvas {

    public int lines = 0;
    public int arcs = 0;

    @Override
    public void drawEgg(Vector3 min, Vector3 end) {
        arcs++;
    }

    @Override
    public void drawEllips(Vector2 start, Vector2 end) {
        arcs++;
    }

    @Override
    public void drawLine(Vector start, Vector end) {
        lines++;
    }

}
