package physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static org.junit.Assert.*;

import nl.rutgerkok.physicssimulation.AxisAlignedBoundingBox;

import org.junit.Test;

import static nl.rutgerkok.physicssimulation.AxisAlignedBoundingBox.aabb;

public class AxisAlignedBoundingBoxTest {

    @Test
    public void testEquality() {
        AxisAlignedBoundingBox box1 = aabb(vec2(2, 1), vec2(5, 3));
        AxisAlignedBoundingBox box2 = aabb(vec2(2, 1), vec2(5, 3));
        AxisAlignedBoundingBox otherBox = aabb(vec2(0, 1), vec2(5, 3));

        assertEquals(box1, box2);
        assertEquals(box1.hashCode(), box2.hashCode());
        assertNotEquals(otherBox, box1);
    }

    @Test
    public void testOverlap() {
        // Two boxes next to each other
        AxisAlignedBoundingBox box1 = aabb(vec2(0, 0), vec2(3, 3));
        AxisAlignedBoundingBox box2 = aabb(vec2(3, 0), vec2(6, 3));

        // A box on top of both
        AxisAlignedBoundingBox boxOnTop = aabb(vec2(1, 1), vec2(5, 2));

        assertFalse(box1.overlapsWith(box2));
        assertFalse(box2.overlapsWith(box1));
        assertTrue(box1.overlapsWith(boxOnTop));
        assertTrue(box2.overlapsWith(boxOnTop));
        assertTrue(boxOnTop.overlapsWith(box1));
        assertTrue(boxOnTop.overlapsWith(box2));
    }

    @Test
    public void testSelfOverlap() {
        AxisAlignedBoundingBox box = aabb(vec2(0, 0), vec2(3, 3));
        assertTrue(box.overlapsWith(box));
    }

    @Test(expected = NullPointerException.class)
    public void testNullBox() {
        aabb(vec2(0, 2), null);
    }
}
