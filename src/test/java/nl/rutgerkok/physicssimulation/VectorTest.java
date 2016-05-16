package nl.rutgerkok.physicssimulation;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static org.junit.Assert.assertEquals;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector3;

import org.junit.Test;

public class VectorTest {

    @Test
    public void test3DVector() {
        Vector3 vector = vec3(4, 2, 0);
        assertEquals(4, vector.getX(), 0.0001);
        assertEquals(2, vector.getY(), 0.0001);
        assertEquals(0, vector.getZ(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition23MismatchedDimensions() {
        Vector vector3 = vec3(1, 2, 3);
        Vector vector2 = vec2(1, 2);

        vector2.plus(vector3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition32MismatchedDimensions() {
        Vector vector3 = vec3(1, 2, 3);
        Vector vector2 = vec2(1, 2);

        vector3.plus(vector2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductMismatchedDimensions() {
        Vector vector3 = vec3(1, 2, 3);
        Vector vector2 = vec2(1, 2);

        vector2.dotProduct(vector3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyVector() {
        vec(new double[0]);
    }
}
