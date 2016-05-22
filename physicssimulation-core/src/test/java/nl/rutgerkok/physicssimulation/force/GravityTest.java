package nl.rutgerkok.physicssimulation.force;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.vector.Vector3;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

import org.junit.Test;

public class GravityTest {

    private PhysicalObject setupObject2D() {
        return obj(circle(vec2(0, 0), 1), vec2(0, 0), Material.BOUNCYBALL);
    }

    private PhysicalObject setupObject3D() {
        return obj(sphere(vec3(0, 0, 0), 5), vec3(0, 0, 0), Material.BOUNCYBALL);
    }

    @Test
    public void testGravity2D() {
        PhysicalObject object = setupObject2D();
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();

        Vector force = Forces.GRAVITY.calculate(object, world);

        assertEquals(0, force.getCoord(0), 0.00001);
        assertEquals(-object.getMass() * 9.81, force.getCoord(1), 0.1);
        assertTrue(force instanceof Vector2);
    }

    @Test
    public void testGravity3D() {
        PhysicalObject object = setupObject3D();
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();

        Vector force = Forces.GRAVITY.calculate(object, world);

        assertEquals(0, force.getCoord(0), 0.00001);
        assertEquals(-object.getMass() * 9.81, force.getCoord(1), 0.1);
        assertEquals(0, force.getCoord(2), 0.00001);
        assertTrue(force instanceof Vector3);
    }

    @Test
    public void testInfiniteMassHasNoGravity() {
        PhysicalObject object = obj(sphere(vec3(0, 0, 0), 5), vec3(0, 0, 0), Material.STATIC);
        PhysicsWorld world = WorldBuilder.newWorld().withObject(object).create();

        Vector force = Forces.GRAVITY.calculate(object, world);

        // infinite mass - object won't fall
        assertEquals(0, force.getCoord(0), 0.01);
        assertEquals(0, force.getCoord(1), 0.01);
        assertEquals(0, force.getCoord(2), 0.01);
    }
}
