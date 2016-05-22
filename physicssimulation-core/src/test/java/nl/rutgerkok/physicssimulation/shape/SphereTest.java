package nl.rutgerkok.physicssimulation.shape;

import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import nl.rutgerkok.physicssimulation.paint.CountingCanvas;

import org.junit.Test;

public class SphereTest {

    @Test
    public void testDrawing() {
        CountingCanvas canvas = new CountingCanvas();
        sphere(vec3(10, 10, 10), 3).toDrawing(canvas);

        assertEquals(1, canvas.arcs);
    }

    @Test
    public void testEquality() {
        Sphere sphere1 = sphere(vec3(30, 30, 30), 20);
        Sphere sphere2 = sphere(vec3(30, 30, 30), 20);

        Sphere otherCenter = sphere(vec3(29, 30, 30), 20);
        Sphere otherRadius = sphere(vec3(30, 30, 30), 19);

        assertEquals(sphere1, sphere1);
        assertEquals(sphere1, sphere2);
        assertEquals(sphere1.hashCode(), sphere2.hashCode());

        assertNotEquals(sphere1, otherCenter);
        assertNotEquals(sphere2, otherRadius);
        assertNotEquals(sphere1, null);
        assertNotEquals(sphere1, "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfinite() {
        sphere(vec3(20, 20, 20), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testMoving() {
        Sphere sphere = sphere(vec3(10, 11, 12), 3);

        assertEquals(sphere(vec3(15, 10, 8), 3), sphere.moved(vec3(5, -1, -4)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRadius() {
        sphere(vec3(10, 10, 10), -4);
    }

    @Test
    public void testProperties() {
        Sphere sphere = sphere(vec3(20, 30, 40), 27);
        assertEquals(27, sphere.getRadius(), 0.00001);
        assertEquals(vec3(20, 30, 40), sphere.getCenter());
    }

    @Test
    public void testToString() {
        assertEquals("sphere(vec3(10.0, 10.0, 0.0), 10.0)", sphere(vec3(10.0, 10.0, 0.0), 10.0).toString());
    }

    @Test
    public void testVolume() {
        Sphere unitSphere = sphere(vec3(0, 0, 0), 1);
        assertEquals(4.0 / 3 * Math.PI, unitSphere.getVolume(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRadius() {
        sphere(vec3(10, 10, 10), 0);
    }

}
