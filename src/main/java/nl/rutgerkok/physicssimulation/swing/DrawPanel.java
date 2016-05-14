package nl.rutgerkok.physicssimulation.swing;

import java.awt.Graphics;
import java.util.Objects;

import javax.swing.JPanel;

import nl.rutgerkok.physicssimulation.paint.Painter;

/**
 * A variant of {@link JPanel} that allows a {@link Painter} to draw.
 *
 */
final class DrawPanel extends JPanel {

    private static final long serialVersionUID = -5973534506179687970L;

    private final Painter painter;

    public DrawPanel(Painter painter) {
        this.painter = Objects.requireNonNull(painter);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        painter.paint(new SwingCanvas(graphics));
    }
}
