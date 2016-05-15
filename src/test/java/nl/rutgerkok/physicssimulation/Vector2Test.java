package nl.rutgerkok.physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector.vec;
import static nl.rutgerkok.physicssimulation.Vector.vec2;
import static nl.rutgerkok.physicssimulation.Vector.vec3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Tests for 2D vectors.
 *
 */
public class Vector2Test {

    @Test
    public void testAddition() {
        Vector oneTwo = vec2(1, 2);
        Vector fiveSix = vec2(5, 6);

        Vector sixEight = vec2(6, 8);

        assertEquals(sixEight, oneTwo.plus(fiveSix));
    }

    @Test
    public void testDimension() {
        vec2(4, 5).checkDimension(2);
    }

    @Test
    public void testDistances() {
        assertEquals(2, vec2(0, 0).getDistanceTo(vec2(0, 2)), 0.0001);
        assertEquals(2, vec2(0, 0).getDistanceTo(vec2(2, 0)), 0.0001);
        assertEquals(5, vec2(0, 0).getDistanceTo(vec2(4, 3)), 0.0001);
    }

    @Test
    public void testDivision() {
        assertEquals(vec2(5, 3), vec2(15, 9).divide(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZero() {
        vec2(3, 4).divide(0);
    }

    @Test
    public void testEquality() {
        Vector fourZero = vec2(4, 0);
        assertEquals(fourZero, fourZero);
        assertEquals(fourZero, vec2(4, 0));
        assertEquals(fourZero, vec(new double[] { 4, 0 }));
        assertEquals(fourZero.hashCode(), vec2(4, 0).hashCode());

        assertNotEquals(fourZero, null);
        assertNotEquals(fourZero, vec2(5, 0));
        assertNotEquals(fourZero, vec3(5, 0, 0));
        assertNotEquals(fourZero, "foo");
    }

    @Test
    public void testFields() {
        Vector sevenEleven = vec2(7, 11);
        assertEquals(7, sevenEleven.getX(), 0.000001);
        assertEquals(11, sevenEleven.getY(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfiniteX() {
        vec2(Double.POSITIVE_INFINITY, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDimension() {
        vec2(4, 5).checkDimension(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInvalidField() {
        vec2(7, 11).getZ();
    }

    @Test
    public void testMultiplication() {
        assertEquals(vec2(2, 4), vec2(1, 2).multiply(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNY() {
        vec2(1, Double.NaN);
    }

    @Test
    public void testNormalize() {
        assertEquals(vec2(-1, 0), vec2(-18, 0).normalized());
    }

    @Test
    public void testToString() {
        assertEquals("vec2(3.0, 4.0)", vec2(3.0, 4.0).toString());
    }
}
