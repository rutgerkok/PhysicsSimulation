package nl.rutgerkok.physicssimulation.swing;

import java.awt.Graphics;
import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector;
import nl.rutgerkok.physicssimulation.paint.Canvas;

final class SwingCanvas implements Canvas {

    private final Graphics graphics;

    public SwingCanvas(Graphics graphics) {
        this.graphics = Objects.requireNonNull(graphics);
    }

    @Override
    public void drawLine(Vector start, Vector end) {
        graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    @Override
    public void drawArc(Vector center, Vector size, double startAngle, double endAngle) {
        graphics.drawArc((int) center.getX(), (int) center.getY(),
                (int) size.getX(), (int) size.getY(),
                (int) Math.toDegrees(startAngle), (int) Math.toDegrees(endAngle));
    }

}
