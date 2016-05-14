package physicssimulation;

import static org.junit.Assert.*;

import nl.rutgerkok.physicssimulation.AxisAlignedBoundingBox;
import nl.rutgerkok.physicssimulation.Vector2;

import org.junit.Test;

public class AxisAlignedBoundingBoxTest {

    @Test
    public void testEquality() {
        AxisAlignedBoundingBox box1 = AxisAlignedBoundingBox.of(Vector2.of(2, 1), Vector2.of(5, 3));
        AxisAlignedBoundingBox box2 = AxisAlignedBoundingBox.of(Vector2.of(2, 1), Vector2.of(5, 3));
        AxisAlignedBoundingBox otherBox = AxisAlignedBoundingBox.of(Vector2.of(0, 1), Vector2.of(5, 3));

        assertEquals(box1, box2);
        assertEquals(box1.hashCode(), box2.hashCode());
        assertNotEquals(otherBox, box1);
    }
}
