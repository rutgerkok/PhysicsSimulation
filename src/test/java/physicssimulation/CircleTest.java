package physicssimulation;

import static nl.rutgerkok.physicssimulation.Circle.circle;
import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import nl.rutgerkok.physicssimulation.Circle;

import org.junit.Test;

public class CircleTest {

    @Test(expected = NullPointerException.class)
    public void testNull() {
        circle(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfinite() {
        circle(vec2(20, 20), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testEquality() {
        Circle circle1 = circle(vec2(30, 30), 20);
        Circle circle2 = circle(vec2(30, 30), 20);

        Circle otherCenter = circle(vec2(29, 30), 20);
        Circle otherRadius = circle(vec2(30, 30), 19);

        assertEquals(circle1, circle2);
        assertEquals(circle1.hashCode(), circle2.hashCode());

        assertNotEquals(circle1, otherCenter);
        assertNotEquals(circle2, otherRadius);
    }

    @Test
    public void testOverlap() {
        Circle left = circle(vec2(-10, 0), 10);
        Circle right = circle(vec2(10, 0), 10);

        Circle center = circle(vec2(0, 0), 10);

        assertFalse(left.overlapsWith(right));
        assertTrue(center.overlapsWith(left));
        assertTrue(right.overlapsWith(center));
    }
}
