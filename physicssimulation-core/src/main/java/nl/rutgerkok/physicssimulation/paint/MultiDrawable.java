package nl.rutgerkok.physicssimulation.paint;

import java.util.Collection;
import java.util.Objects;

/**
 * Draws a collection of objects in the order defined by their iterator.
 *
 */
public final class MultiDrawable implements Drawable {

    private final Collection<? extends Drawable> drawables;

    /**
     * Creates a new drawable of the given drawables.
     * @param drawables The drawables.
     */
    public MultiDrawable(Collection<? extends Drawable> drawables) {
        this.drawables = Objects.requireNonNull(drawables);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        drawables.forEach(drawable -> drawable.toDrawing(canvas));
    }
}
