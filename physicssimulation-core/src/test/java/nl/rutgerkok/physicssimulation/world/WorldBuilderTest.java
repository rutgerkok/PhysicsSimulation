package nl.rutgerkok.physicssimulation.world;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertNotNull;

import nl.rutgerkok.physicssimulation.shape.Material;

import org.junit.Test;

public class WorldBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testDimensionMismatch() {
        // Cannot add objects in different dimensions (2D vs 3D) in one world
        WorldBuilder.newWorld()
                .withObject(obj(sphere(vec3(0, 1, -10), 2), vec3(0, 0, 6), Material.METAL))
                .withObject(obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.WOOD));
    }

    @Test
    public void testToString() {
        assertNotNull(WorldBuilder.newWorld().toString());
    }
}
