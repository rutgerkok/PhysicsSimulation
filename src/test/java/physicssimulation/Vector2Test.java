package physicssimulation;

import static org.junit.Assert.*;

import nl.rutgerkok.physicssimulation.Vector2;

import org.junit.Test;

public class Vector2Test {

    @Test
    public void testAddition() {
        Vector2 oneTwo = Vector2.of(1, 2);
        Vector2 fiveSix = Vector2.of(5, 6);

        Vector2 sixEight = Vector2.of(6, 8);

        assertEquals(sixEight, oneTwo.plus(fiveSix));
    }
}
