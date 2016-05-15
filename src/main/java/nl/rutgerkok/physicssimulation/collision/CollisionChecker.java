package nl.rutgerkok.physicssimulation.collision;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import nl.rutgerkok.physicssimulation.MoreMath;
import nl.rutgerkok.physicssimulation.shape.Circle;
import nl.rutgerkok.physicssimulation.shape.Rectangle;
import nl.rutgerkok.physicssimulation.shape.Shape;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector2;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Checks for collisions between objects. Based on <a href=
 * "http://gamedevelopment.tutsplus.com/tutorials/how-to-create-a-custom-2d-physics-engine-the-basics-and-impulse-resolution--gamedev-6331">
 * this tutorial</a>.
 *
 * @see #getCollisions(Collection)
 */
public final class CollisionChecker {

    private @Nullable Collision checkCollision(PhysicalObject a, PhysicalObject b) {
        Shape shapeA = a.getShape();
        Shape shapeB = b.getShape();

        if (shapeA instanceof Circle) {
            if (shapeB instanceof Circle) {
                return checkCollisionBetweenCircles(a, b);
            }
            if (shapeB instanceof Rectangle) {
                return checkCollisionBetweenRectangleAndCircle(b, a);
            }
        }
        if (shapeA instanceof Rectangle) {
            if (shapeB instanceof Rectangle) {
                return checkCollisionBetweenRectangles(a, b);
            }
            if (shapeB instanceof Circle) {
                return checkCollisionBetweenRectangleAndCircle(a, b);
            }
        }

        return null;
    }

    private @Nullable Collision checkCollisionBetweenCircles(PhysicalObject objA, PhysicalObject objB) {
        Circle a = (Circle) objA.getShape();
        Circle b = (Circle) objB.getShape();

        Vector centerDifference = b.getCenter().minus(a.getCenter());

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
            Vector normal = centerDifference.divide(distanceBetweenCenters);
            return new Collision(objA, objB, penetration, normal);
        } else {
            // Circles are on same position
            // Choose random (but consistent) values
            return new Collision(objA, objB, a.getRadius(), vec2(1, 0));
        }
    }

    private @Nullable Collision checkCollisionBetweenRectangleAndCircle(PhysicalObject objA, PhysicalObject objB) {
        // Setup a couple pointers to each object
        Rectangle rectangle = (Rectangle) objA.getShape();
        Circle circle = (Circle) objB.getShape();

        // Vector from A to B
        Vector2 distanceBetweenCenters = circle.getCenter().minus(rectangle.getCenter());

        // Closest point on A to center of B
        Vector2 clampedDistanceBetweenCenters = distanceBetweenCenters;

        // Calculate half extents along each axis
        double halfRectangleXSize = rectangle.getXSize() / 2;
        double halfRectangleYSize = rectangle.getYSize() / 2;

        // Clamp point to edges of the rectangle
        clampedDistanceBetweenCenters = vec2(
                MoreMath.clamp(-halfRectangleXSize, halfRectangleXSize, clampedDistanceBetweenCenters.getX()),
                MoreMath.clamp(-halfRectangleYSize, halfRectangleYSize, clampedDistanceBetweenCenters.getY()));

        boolean circleCenterIsInsideRectangle = false;

        // Circle is inside the AABB, so we need to clamp the circle's center
        // to the closest edge
        if (distanceBetweenCenters.equals(clampedDistanceBetweenCenters)) {
            circleCenterIsInsideRectangle = true;

            // Find closest axis
            if (Math.abs(distanceBetweenCenters.getX()) > Math.abs(distanceBetweenCenters.getY())) {
                // Clamp to closest extent
                if (clampedDistanceBetweenCenters.getX() > 0)
                    clampedDistanceBetweenCenters = vec2(halfRectangleXSize,
                            clampedDistanceBetweenCenters.getY());
                else
                    clampedDistanceBetweenCenters = vec2(-halfRectangleXSize,
                            clampedDistanceBetweenCenters.getY());
            }

            // y axis is shorter
            else {
                // Clamp to closest extent
                if (clampedDistanceBetweenCenters.getY() > 0)
                    clampedDistanceBetweenCenters = vec2(
                            clampedDistanceBetweenCenters.getX(), halfRectangleYSize);
                else
                    clampedDistanceBetweenCenters = vec2(
                            clampedDistanceBetweenCenters.getX(), -halfRectangleYSize);
            }
        }

        Vector normal = distanceBetweenCenters.minus(clampedDistanceBetweenCenters);
        double squaredNormalLength = normal.getSquaredLength();
        double circleRadius = circle.getRadius();

        // Early out of the radius is shorter than distance to closest point and
        // Circle not inside the AABB
        if (squaredNormalLength >= circleRadius * circleRadius && !circleCenterIsInsideRectangle)
            return null;

        // Collision normal needs to be flipped to point outside if circle was
        // inside the AABB
        if (circleCenterIsInsideRectangle) {
            normal = normal.multiply(-1);
        }

        double penetration = circleRadius - Math.sqrt(squaredNormalLength);

        return new Collision(objA, objB, penetration, normal.normalized());
    }

    private @Nullable Collision checkCollisionBetweenRectangles(PhysicalObject objA, PhysicalObject objB) {
        Rectangle a = (Rectangle) objA.getShape();
        Rectangle b = (Rectangle) objB.getShape();

        // Vector from A to B
        Vector2 n = b.getCenter().minus(a.getCenter());

        // Calculate half extents along x axis for each object
        double aExtent = a.getXSize() / 2;
        double bExtent = b.getXSize() / 2;

        // Calculate overlap on x axis
        double xOverlap = aExtent + bExtent - Math.abs(n.getX());

        // SAT test on x axis
        if (xOverlap <= 0) {
            return null;
        }

        aExtent = a.getYSize() / 2;
        bExtent = b.getYSize() / 2;

        // Calculate overlap on y axis
        double yOverlap = aExtent + bExtent - Math.abs(n.getY());

        // SAT test on y axis
        if (yOverlap <= 0) {
            return null;
        }

        // Find out which axis is axis of least penetration
        Vector normal;
        double penetration;
        if (xOverlap < yOverlap) {
            // Point towards B knowing that n points from A to B
            if (n.getX() < 0)
                normal = vec2(-1, 0);
            else
                normal = vec2(1, 0);

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