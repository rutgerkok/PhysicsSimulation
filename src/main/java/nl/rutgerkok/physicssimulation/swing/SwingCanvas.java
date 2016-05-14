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
        graphics.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

}
