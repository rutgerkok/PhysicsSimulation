package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Objects;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.vector.Vector3;

/**
 * Implementation of {@link Canvas} that draws using nothing more than a
 * {@link Graphics} instance. 3D drawing is implemented using a simple
 * projection.
 *
 */
public final class SwingCanvas implements Canvas {

    private final Graphics graphics;
    private final Dimension canvasSize;
    private final Vector2 zProjection = vec2(-1, -0.4);
    private final double scale = 10;

    public SwingCanvas(Graphics graphics, Dimension canvasSize) {
        this.graphics = Objects.requireNonNull(graphics);
        this.canvasSize = Objects.requireNonNull(canvasSize);

        // Draw axis
        drawLine(vec3(0, 0, 0), vec3(20, 0, 0)); // x-axis
        drawLine(vec3(0, 0, 0), vec3(0, 20, 0)); // y-axis
        drawLine(vec3(0, 0, 0), vec3(0, 0, 20)); // z-axis
    }

    @Override
    public void drawEgg(Vector3 min, Vector3 max) {
        Vector3 center = min.plus(max).divide(2);
        Vector3 radius = center.minus(min);
        Vector2 projectedCenter = projectOnPlane(center);
        Vector2 projectedRadius = vec2(radius.getX(), radius.getY());
        drawEllips(projectedCenter.minus(projectedRadius), projectedCenter.plus(projectedRadius));
    }

    @Override
    public void drawEllips(Vector2 min, Vector2 max) {
        int panelXStart = x(min);
        int panelYStart = y(max);
        int panelXEnd = x(max);
        int panelYEnd = y(min);

        graphics.drawArc(panelXStart, panelYStart,
                panelXEnd - panelXStart, panelYEnd - panelYStart,
                0, 360);
    }

    @Override
    public void drawLine(Vector2 start, Vector2 end) {
        graphics.drawLine(x(start), y(start), x(end), y(end));
    }

    private void drawLine(Vector3 start, Vector3 end) {
        drawLine(projectOnPlane(start), projectOnPlane(end));
    }

    private Vector2 projectOnPlane(Vector3 vector) {
        Vector2 vector2 = vec2(vector.getX(), vector.getY());
        return vector2.plus(zProjection.multiply(vector.getZ()));
    }

    private int x(Vector2 vector) {
        return (int) (vector.getX() * scale + canvasSize.width / 2);
    }

    private int y(Vector2 vector) {
        return (int) (canvasSize.height / 2 - vector.getY() * scale);
    }

}
