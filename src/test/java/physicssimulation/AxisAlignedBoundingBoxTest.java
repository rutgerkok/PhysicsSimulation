package physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
}
