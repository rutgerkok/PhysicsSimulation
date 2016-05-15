package nl.rutgerkok.physicssimulation.shape;

import static nl.rutgerkok.physicssimulation.Vector.vec2;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class RectangleTest {

    @Test
    public void testArea() {
        Rectangle rectangle = rectangle(vec2(0, 0), vec2(4, 2));
        assertEquals(8, rectangle.getArea(), 0.00001);
    }

    @Test
    public void testCenter() {
        Rectangle rectangle = rectangle(vec2(-4, -4), vec2(4, 4));
        assertEquals(vec2(0, 0), rectangle.getCenter());
    }

    @Test
    public void testEquality() {
        Rectangle box1 = rectangle(vec2(2, 1), vec2(5, 3));
        Rectangle box2 = rectangle(vec2(2, 1), vec2(5, 3));
        Rectangle completelyDifferentBox = rectangle(vec2(0, 1), vec2(5, 3));
        Rectangle slightlyDifferentBox = rectangle(vec2(2, 1), vec2(5, 3.5));

        assertEquals(box1, box1);
        assertEquals(box1, box2);
        assertEquals(box1.hashCode(), box2.hashCode());
        assertNotEquals(completelyDifferentBox, box1);
        assertNotEquals(box1, null);
        assertNotEquals(box1, "foo");
        assertNotEquals(box1, slightlyDifferentBox);
    }

}
