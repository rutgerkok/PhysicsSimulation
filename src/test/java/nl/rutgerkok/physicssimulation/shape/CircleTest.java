package nl.rutgerkok.physicssimulation.shape;

import static nl.rutgerkok.physicssimulation.Vector.vec2;
import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import nl.rutgerkok.physicssimulation.TestCanvas;

import org.junit.Test;

public class CircleTest {

    @Test
    public void testArea() {
        Circle unitCircle = circle(vec2(0, 0), 1);
        assertEquals(Math.PI, unitCircle.getArea(), 0.00001);
    }

    @Test
    public void testDrawing() {
        TestCanvas canvas = new TestCanvas();
        circle(vec2(10, 10), 3).toDrawing(canvas);

        assertEquals(1, canvas.arcs);
    }

    @Test
    public void testEquality() {
        Circle circle1 = circle(vec2(30, 30), 20);
        Circle circle2 = circle(vec2(30, 30), 20);

        Circle otherCenter = circle(vec2(29, 30), 20);
        Circle otherRadius = circle(vec2(30, 30), 19);

        assertEquals(circle1, circle1);
        assertEquals(circle1, circle2);
        assertEquals(circle1.hashCode(), circle2.hashCode());

        assertNotEquals(circle1, otherCenter);
        assertNotEquals(circle2, otherRadius);
        assertNotEquals(circle1, null);
        assertNotEquals(circle1, "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfinite() {
        circle(vec2(20, 20), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testMoving() {
        Circle circle = circle(vec2(10, 11), 3);

        assertEquals(circle(vec2(15, 10), 3), circle.moved(vec2(5, -1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRadius() {
        circle(vec2(10, 10), -4);
    }

    @Test
    public void testProperties() {
        Circle circle = circle(vec2(20, 30), 27);
        assertEquals(27, circle.getRadius(), 0.00001);
        assertEquals(vec2(20, 30), circle.getCenter());
    }

    @Test
    public void testToString() {
        assertEquals("circle(vec2(10.0, 10.0), 10.0)", circle(vec2(10.0, 10.0), 10.0).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRadius() {
        circle(vec2(10, 10), 0);
    }

}
