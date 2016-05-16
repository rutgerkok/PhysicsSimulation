package nl.rutgerkok.physicssimulation;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector3;

import org.junit.Test;

/**
 * Tests for 2D vectors.
 *
 */
public class Vector3Test {

    @Test
    public void testAddition() {
        Vector oneTwoThree = vec3(1, 2, 3);
        Vector fiveSixSeven = vec3(5, 6, 7);

        Vector sixEightTen = vec3(6, 8, 10);

        assertEquals(sixEightTen, oneTwoThree.plus(fiveSixSeven));
    }

    @Test
    public void testDimension() {
        assertTrue(vec3(4, 5, 6) instanceof Vector3);
        assertTrue(vec(new double[] { 4, 5, 6 }) instanceof Vector3);
    }

    @Test
    public void testDistances() {
        assertEquals(2, vec3(0, 0, 0).getDistanceTo(vec3(0, 2, 0)), 0.0001);
        assertEquals(2, vec3(0, 0, 0).getDistanceTo(vec3(2, 0, 0)), 0.0001);
        assertEquals(5, vec3(0, 0, 0).getDistanceTo(vec3(4, 3, 0)), 0.0001);
    }

    @Test
    public void testDivision() {
        assertEquals(vec3(5, 3, 1), vec3(15, 9, 3).divide(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZero() {
        vec3(3, 4, 5).divide(0);
    }

    @Test
    public void testEquality() {
        Vector fourZeroSix = vec3(4, 0, 6);
        assertEquals(fourZeroSix, fourZeroSix);
        assertEquals(fourZeroSix, vec3(4, 0, 6));
        assertEquals(fourZeroSix, vec(new double[] { 4, 0, 6 }));
        assertEquals(fourZeroSix.hashCode(), vec3(4, 0, 6).hashCode());

        assertNotEquals(fourZeroSix, null);
        assertNotEquals(fourZeroSix, vec2(5, 0));
        assertNotEquals(fourZeroSix, vec3(5, 0, 0));
        assertNotEquals(fourZeroSix, vec3(4, 1, 0));
        assertNotEquals(fourZeroSix, vec3(4, 0, 7));
        assertNotEquals(fourZeroSix, "foo");
    }

    @Test
    public void testFields() {
        Vector3 sevenElevenZero = vec3(7, 11, 0);
        assertEquals(7, sevenElevenZero.getX(), 0.000001);
        assertEquals(11, sevenElevenZero.getY(), 0.000001);
        assertEquals(0, sevenElevenZero.getZ(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfiniteX() {
        vec3(1, Double.POSITIVE_INFINITY, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidField() {
        vec3(7, 11, 0).getCoord(3);
    }

    @Test
    public void testMultiplication() {
        assertEquals(vec3(2, 4, 6), vec3(1, 2, 3).multiply(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNZ() {
        vec3(1, 2, Double.NaN);
    }

    @Test
    public void testNormalize() {
        assertEquals(vec3(-1, 0, 0), vec3(-18, 0, 0).normalized());
    }

    @Test
    public void testToString() {
        assertEquals("vec3(3.0, 4.0, 5.0)", vec3(3.0, 4.0, 5.0).toString());
    }
}
