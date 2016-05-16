package nl.rutgerkok.physicssimulation.swing;

import java.awt.Graphics;
import java.util.Objects;

import javax.swing.JPanel;

import nl.rutgerkok.physicssimulation.paint.Drawable;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A variant of {@link JPanel} that allows a {@link Drawable} to draw.
 *
 */
final class DrawPanel extends JPanel {

    private static final long serialVersionUID = -5973534506179687970L;

    private final Drawable painter;

    public DrawPanel(Drawable painter) {
        this.painter = Objects.requireNonNull(painter);
    }

    @Override
    public void paintComponent(@Nullable Graphics graphics) {
        Objects.requireNonNull(graphics);
        painter.toDrawing(new SwingCanvas(graphics, getSize()));
    }
}
