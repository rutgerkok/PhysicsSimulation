package physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import nl.rutgerkok.physicssimulation.shape.Rectangle;

import org.junit.Test;

public class RectangleTest {

    @Test
    public void testEquality() {
        Rectangle box1 = rectangle(vec2(2, 1), vec2(5, 3));
        Rectangle box2 = rectangle(vec2(2, 1), vec2(5, 3));
        Rectangle otherBox = rectangle(vec2(0, 1), vec2(5, 3));

        assertEquals(box1, box2);
        assertEquals(box1.hashCode(), box2.hashCode());
        assertNotEquals(otherBox, box1);
    }

    @Test
    public void testOverlap() {
        // Two boxes next to each other
        Rectangle box1 = rectangle(vec2(0, 0), vec2(3, 3));
        Rectangle box2 = rectangle(vec2(3, 0), vec2(6, 3));

        // A box on top of both
        Rectangle boxOnTop = rectangle(vec2(1, 1), vec2(5, 2));

        assertFalse(box1.overlapsWith(box2));
        assertFalse(box2.overlapsWith(box1));
        assertTrue(box1.overlapsWith(boxOnTop));
        assertTrue(box2.overlapsWith(boxOnTop));
        assertTrue(boxOnTop.overlapsWith(box1));
        assertTrue(boxOnTop.overlapsWith(box2));
    }

    @Test
    public void testSelfOverlap() {
        Rectangle box = rectangle(vec2(0, 0), vec2(3, 3));
        assertTrue(box.overlapsWith(box));
    }

    @Test(expected = NullPointerException.class)
    public void testNullBox() {
        rectangle(vec2(0, 2), null);
    }

    @Test
    public void testCenter() {
        Rectangle rectangle = rectangle(vec2(-4, -4), vec2(4, 4));
        assertEquals(vec2(0, 0), rectangle.getCenter());
    }

    @Test
    public void testArea() {
        Rectangle rectangle = rectangle(vec2(0, 0), vec2(4, 2));
        assertEquals(8, rectangle.getArea(), 0.00001);
    }
}
