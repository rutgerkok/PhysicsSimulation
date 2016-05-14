package physicssimulation;

import static org.junit.Assert.*;

import nl.rutgerkok.physicssimulation.Vector2;

import org.junit.Test;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

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
}
