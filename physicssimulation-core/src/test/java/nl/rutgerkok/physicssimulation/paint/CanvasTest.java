package nl.rutgerkok.physicssimulation.paint;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CanvasTest {

    @Test
    public void testDrawRectangle() {
        // A rectangle consists of four lines
        CountingCanvas canvas = new CountingCanvas();

        canvas.drawRectangle(vec2(0, 0), vec2(2, 2));

        assertEquals(4, canvas.lines);
    }
}
