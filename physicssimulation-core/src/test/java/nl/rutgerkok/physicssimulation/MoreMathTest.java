package nl.rutgerkok.physicssimulation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoreMathTest {

    @Test
    public void testClamp() {
        assertEquals(-3, MoreMath.clamp(-3, 2, -5), 0.000001);
        assertEquals(2, MoreMath.clamp(-3, 2, 5), 0.000001);
        assertEquals(1, MoreMath.clamp(-3, 2, 1), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidClampLimits() {
        MoreMath.clamp(5, 4, 4.5);
    }

    @Test(expected = RuntimeException.class)
    public void testNoInstances() {
        new MoreMath();
    }
}
