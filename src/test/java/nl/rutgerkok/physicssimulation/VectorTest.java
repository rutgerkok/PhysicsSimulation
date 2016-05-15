package nl.rutgerkok.physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector.vec2;
import static nl.rutgerkok.physicssimulation.Vector.vec3;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VectorTest {

    @Test
    public void test3DVector() {
        Vector vector = vec3(4, 2, 0);
        assertEquals(4, vector.getX(), 0.0001);
        assertEquals(2, vector.getY(), 0.0001);
        assertEquals(0, vector.getZ(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMismatchedDimensions() {
        Vector vector3 = vec3(1, 2, 3);
        Vector vector2 = vec2(1, 2);

        vector2.plus(vector3);
    }
}
