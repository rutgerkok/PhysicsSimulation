package nl.rutgerkok.physicssimulation;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.vector.Vector2;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Fake drawing canvas that just keeps trac of some statistics.
 *
 */
public class TestCanvas implements Canvas {

    public int lines = 0;
    public int arcs = 0;

    @Override
    public void drawArc(@NonNull Vector2 center, @NonNull Vector2 size, double startAngle, double endAngle) {
        arcs++;
    }

    @Override
    public void drawLine(@NonNull Vector2 start, @NonNull Vector2 end) {
        lines++;
    }

}
