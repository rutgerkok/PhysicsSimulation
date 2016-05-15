package nl.rutgerkok.physicssimulation.collision;

import static java.util.Arrays.asList;
import static nl.rutgerkok.physicssimulation.Vector.vec2;
import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import nl.rutgerkok.physicssimulation.Vector;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

import org.junit.Test;

public class CollisionTest {

    @Test
    public void testCircleOverlap() {
        Vector zero = vec2(0, 0);

        PhysicalObject left = obj(circle(vec2(-10, 0), 10), zero);
        PhysicalObject right = obj(circle(vec2(10, 0), 10), zero);
        PhysicalObject center = obj(circle(vec2(0, 0), 10), zero);

        // Check for collisions
        List<PhysicalObject> objects = asList(left, right, center);
        Set<Collision> collisions = new CollisionChecker().getCollisions(objects);

        assertEquals(2, collisions.size());
        assertTrue(collisions.contains(new Collision(left, center, 1, zero)));
        assertTrue(collisions.contains(new Collision(right, center, 1, zero)));
    }

    @Test
    public void testRectangleOverlap() {
        Vector zero = vec2(0, 0);

        // Two boxes next to each other
        PhysicalObject left = obj(rectangle(vec2(0, 0), vec2(3, 3)), zero);
        PhysicalObject right = obj(rectangle(vec2(3, 0), vec2(6, 3)), zero);

        // A box on top of both
        PhysicalObject boxOnTop = obj(rectangle(vec2(1, 1), vec2(5, 2)), zero);

        // Check for collisions
        List<PhysicalObject> objects = asList(left, right, boxOnTop);
        Set<Collision> collisions = new CollisionChecker().getCollisions(objects);

        assertEquals(2, collisions.size());
        assertTrue(collisions.contains(new Collision(left, boxOnTop, 1, zero)));
        assertTrue(collisions.contains(new Collision(right, boxOnTop, 1, zero)));
    }
}
