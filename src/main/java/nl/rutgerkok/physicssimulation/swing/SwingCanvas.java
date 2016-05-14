package nl.rutgerkok.physicssimulation.swing;

import java.awt.Graphics;
import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.paint.Canvas;

final class SwingCanvas implements Canvas {

    private final Graphics graphics;

    public SwingCanvas(Graphics graphics) {
        this.graphics = Objects.requireNonNull(graphics);
    }

    @Override
    public void drawLine(Vector2 start, Vector2 end) {
        graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    @Override
    public void drawArc(Vector2 center, Vector2 size, double startAngle, double endAngle) {
        graphics.drawArc((int) center.getX(), (int) center.getY(),
                (int) size.getX(), (int) size.getY(),
                (int) Math.toDegrees(startAngle), (int) Math.toDegrees(endAngle));
    }

}
