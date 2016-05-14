package nl.rutgerkok.physicssimulation.paint;

/**
 * Something that can paint on a canvas.
 *
 */
public interface Painter {

    /**
     * Called when the painter should paint on the canvas.
     * @param canvas The canvas.
     */
    void paint(Canvas canvas);
}
