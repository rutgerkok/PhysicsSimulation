package nl.rutgerkok.physicssimulation.collision;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.shape.Circle;
import nl.rutgerkok.physicssimulation.shape.Rectangle;
import nl.rutgerkok.physicssimulation.shape.Shape;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

import org.eclipse.jdt.annotation.Nullable;

public final class CollisionChecker {

    @Nullable
    private Collision checkCollision(PhysicalObject a, PhysicalObject b) {
        Shape shapeA = a.getShape();
        Shape shapeB = b.getShape();

        if (shapeA instanceof Circle) {
            if (shapeB instanceof Circle) {
                return checkCollisionBetweenCircles(a, b);
            }
        }
        if (shapeA instanceof Rectangle) {
            if (shapeB instanceof Rectangle) {
                return checkCollisionBetweenRectangles(a, b);
            }
        }

        return null;
    }

    @Nullable
    private Collision checkCollisionBetweenCircles(PhysicalObject objA, PhysicalObject objB) {
        Circle a = (Circle) objA.getShape();
        Circle b = (Circle) objB.getShape();

        Vector2 centerDifference = b.getCenter().minus(a.getCenter());

        double radiusSum = a.getRadius() + b.getRadius();
        double squaredRadiusSum = radiusSum * radiusSum;
        double squaredDistanceBetweenCenters = centerDifference.getSquaredLength();

        if (squaredDistanceBetweenCenters >= squaredRadiusSum) {
            // No collision, exit
            return null;
        }

        // Circles have collided, now compute manifold
        double distanceBetweenCenters = Math.sqrt(squaredDistanceBetweenCenters);

        // If distance between circles is not zero
        if (distanceBetweenCenters != 0) {
            // Penetration is difference between radius and distance
            double penetration = radiusSum - distanceBetweenCenters;

            // Calculate unit vector from b to a
            Vector2 normal = centerDifference.divide(distanceBetweenCenters);
            return new Collision(objA, objB, penetration, normal);
        } else {
            // Circles are on same position
            // Choose random (but consistent) values
            return new Collision(objA, objB, a.getRadius(), vec2(1, 0));
        }
    }

    @Nullable
    private Collision checkCollisionBetweenRectangles(PhysicalObject objA, PhysicalObject objB) {
        // Setup a couple pointers to each object
        Rectangle a = (Rectangle) objA.getShape();
        Rectangle b = (Rectangle) objB.getShape();

        // Vector from A to B
        Vector2 n = b.getCenter().minus(a.getCenter());

        // Check overlap on x-axis
        double halfXSizeA = a.getXSize() / 2;
        double halfXSizeB = b.getXSize() / 2;
        double xOverlap = halfXSizeA + halfXSizeB - Math.abs(n.getX());
        if (xOverlap <= 0) {
            return null;
        }

        // Check overlap on y-axis
        double halfYSizeA = a.getYSize() / 2;
        double halfYSizeB = b.getYSize() / 2;
        double yOverlap = halfYSizeA + halfYSizeB - Math.abs(n.getY());
        if (yOverlap <= 0) {
            return null;
        }

        // Find out which axis is axis of least penetration
        Vector2 normal;
        double penetration;
        if (xOverlap < yOverlap) {
            // Point towards B knowing that n points from A to B

            if (n.getX() < 0)
                normal = vec2(-1, 0);
            else
                normal = vec2(0, 0);
            penetration = xOverlap;
        } else {
            // Point toward B knowing that n points from A to B
            if (n.getY() < 0)
                normal = vec2(0, -1);
            else
                normal = vec2(0, 1);
            penetration = yOverlap;
        }

        return new Collision(objA, objB, penetration, normal);
    }

    /**
     * Gets all collisions between the given objects.
     * 
     * @param objects
     *            The objects.
     * @return All collisions.
     */
    public Set<Collision> getCollisions(Collection<PhysicalObject> objects) {
        Set<Collision> collisions = new HashSet<>();
        for (PhysicalObject oneObject : objects) {
            for (PhysicalObject otherObject : objects) {
                if (oneObject == otherObject) {
                    continue;
                }
                Collision collision = checkCollision(oneObject, otherObject);
                if (collision != null) {
                    collisions.add(collision);
                }
            }
        }
        return collisions;
    }

}