package nl.rutgerkok.physicssimulation.paint;

import nl.rutgerkok.physicssimulation.Vector2;

public final class TestPainter implements Painter {

    @Override
    public void paint(Canvas canvas) {
        canvas.drawLine(Vector2.of(10, 10), Vector2.of(50, 50));
    }

}
