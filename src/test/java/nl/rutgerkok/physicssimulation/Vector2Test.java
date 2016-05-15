package nl.rutgerkok.physicssimulation;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static org.junit.Assert.assertEquals;

import nl.rutgerkok.physicssimulation.Vector2;

import org.junit.Test;

public class Vector2Test {

    @Test
    public void testAddition() {
        Vector2 oneTwo = vec2(1, 2);
        Vector2 fiveSix = vec2(5, 6);

        Vector2 sixEight = vec2(6, 8);

        assertEquals(sixEight, oneTwo.plus(fiveSix));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfiniteX() {
        vec2(Double.POSITIVE_INFINITY, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNY() {
        vec2(1, Double.NaN);
    }

    @Test
    public void testDistances() {
        assertEquals(2, vec2(0, 0).getDistanceTo(vec2(0, 2)), 0.0001);
        assertEquals(2, vec2(0, 0).getDistanceTo(vec2(2, 0)), 0.0001);
        assertEquals(5, vec2(0, 0).getDistanceTo(vec2(4, 3)), 0.0001);
    }

    @Test
    public void testMultiplication() {
        assertEquals(vec2(2, 4), vec2(1, 2).multiply(2));
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
    public void testNormalize() {
        assertEquals(vec2(-1, 0), vec2(-18, 0).normalized());
    }
}
