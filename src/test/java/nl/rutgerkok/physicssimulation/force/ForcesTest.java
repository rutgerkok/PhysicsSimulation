package nl.rutgerkok.physicssimulation.force;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

import org.junit.Test;

public class ForcesTest {

    @Test
    public void testCombination() {
        PhysicalObject object = obj(circle(vec2(0, 0), 5), vec2(0, 0), Material.BOUNCYBALL);
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();
        Force force1 = (par1, par2) -> vec2(1, 2);
        Force force2 = (par1, par2) -> vec2(4, -3);

        Force resultant = Forces.combine(Arrays.asList(force1, force2));

        // vec2(5, -1) is the sum of both forces
        assertEquals(vec2(5, -1), resultant.calculate(object, world));
    }

    @Test(expected = RuntimeException.class)
    public void testInstance() {
        new Forces();
    }

    @Test
    public void testToString() {
        assertEquals("Forces.ZERO", Forces.ZERO.toString());
        assertEquals("Forces.GRAVITY", Forces.GRAVITY.toString());
    }
}