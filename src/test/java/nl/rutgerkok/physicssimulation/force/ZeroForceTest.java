package nl.rutgerkok.physicssimulation.force;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertEquals;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

import org.junit.Test;

public class ZeroForceTest {

    private PhysicalObject setupObject2D() {
        return obj(circle(vec2(0, 0), 5), vec2(0, 0), Material.BOUNCYBALL);
    }

    private PhysicalObject setupObject3D() {
        return obj(sphere(vec3(0, 0, 0), 5), vec3(0, 0, 0), Material.BOUNCYBALL);
    }

    @Test
    public void testZeroForce2D() {
        PhysicalObject object = setupObject2D();
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();

        Vector force = Forces.ZERO.calculate(object, world);

        assertEquals(vec2(0, 0), force);
    }

    @Test
    public void testZeroForce3D() {
        PhysicalObject object = setupObject3D();
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();

        Vector force = Forces.ZERO.calculate(object, world);

        assertEquals(vec3(0, 0, 0), force);
    }
}
