package nl.rutgerkok.physicssimulation.force;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertEquals;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.vector.Vector3;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

import org.junit.Test;

public class IsotropicInteractionTest {

    private PhysicalObject setupObject(Vector2 position) {
        return obj(circle(position, 1), vec2(0, 0), Material.BOUNCYBALL);
    }

    private PhysicalObject setupObject(Vector3 position) {
        return obj(sphere(position, 5), vec3(0, 0, 0), Material.BOUNCYBALL);
    }

    @Test
    public void testAttraction2D() {
        PhysicalObject obj1 = setupObject(vec2(0, -2));
        PhysicalObject obj2 = setupObject(vec2(0, 2));
        PhysicsWorld world = WorldBuilder.newWorld().withObject(obj1).withObject(obj2).create();

        Vector force = Forces.attraction(200, 2).calculate(obj1, world);

        // F = c / (r ^ p) = 200 / (4 ^ 2) = 200 / 16 = 12.5
        assertEquals(12.5, force.getCoord(1), 0.01);
        assertEquals(0, force.getCoord(0), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtremelyHighPower() {
        Forces.repulsion(10, Short.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAttraction() {
        Forces.attraction(-20, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRepulsion() {
        Forces.repulsion(-20, 3);
    }

    @Test
    public void testRepulsion3D() {
        PhysicalObject obj1 = setupObject(vec3(-2, 0, 0));
        PhysicalObject obj2 = setupObject(vec3(2, 0, 0));
        PhysicsWorld world = WorldBuilder.newWorld().withObject(obj1).withObject(obj2).create();

        Vector force = Forces.repulsion(200, 2).calculate(obj1, world);

        // F = -c / (r ^ p) = -200 / (4 ^ 2) = -200 / 16 = -12.5
        assertEquals(-12.5, force.getCoord(0), 0.01);
        assertEquals(0, force.getCoord(1), 0.01);
        assertEquals(0, force.getCoord(2), 0.01);
    }

    @Test
    public void testToString() {
        assertEquals("Forces.repulsion(100.0, 2)", Forces.repulsion(100.0, 2).toString());
        assertEquals("Forces.attraction(80.0, 2)", Forces.attraction(80.0, 2).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroPower() {
        Forces.repulsion(10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRepulsion() {
        Forces.repulsion(0, 3);
    }
}
