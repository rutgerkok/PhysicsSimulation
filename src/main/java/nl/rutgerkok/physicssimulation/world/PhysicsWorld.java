package nl.rutgerkok.physicssimulation.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector;
import nl.rutgerkok.physicssimulation.collision.Collision;
import nl.rutgerkok.physicssimulation.collision.CollisionChecker;
import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

/**
 * Represents a physics world.
 *
 */
public class PhysicsWorld implements Drawable {

    private final List<PhysicalObject> objects = new ArrayList<>();
    private final CollisionChecker collisionChecker = new CollisionChecker();

    /**
     * Adds a new object to this world.
     * 
     * @param object
     *            The object.
     */
    public void addObject(PhysicalObject object) {
        objects.add(Objects.requireNonNull(object));
    }

    /**
     * Advances the world by the given time step.
     *
     * @param deltaTime
     *            The time step.
     */
    public void advance(double deltaTime) {
        objects.forEach(object -> object.advance(deltaTime));

        resolveCollisions();
    }

    private Vector getCollisionNormal(PhysicalObject a, PhysicalObject b) {
        return b.getShape().getCenter().minus(a.getShape().getCenter()).normalized();
    }

    /**
     * Removes an object from the world. Does nothing if the object was already
     * removed or never added.
     * 
     * @param object
     *            The object.
     */
    public void removeObject(PhysicalObject object) {
        objects.remove(Objects.requireNonNull(object));
    }

    private void resolveCollision(Collision collision) {
        System.out.println("Resolving for " + collision);
        PhysicalObject a = collision.getOneObject();
        PhysicalObject b = collision.getOtherObject();

        Vector collisionNormal = this.getCollisionNormal(a, b);

        // Calculate relative velocity
        Vector velocityDifference = b.getVelocity().minus(a.getVelocity());

        // Calculate relative velocity in terms of the normal direction
        double velAlongNormal = velocityDifference.dotProduct(collisionNormal);

        // Do not resolve if velocities are separating
        if (velAlongNormal > 0)
            return;

        // Calculate restitution
        double bouncyness = Math.min(a.restitution, b.restitution);

        // Calculate impulse scalar
        double impulseLength = -(1 + bouncyness) * velAlongNormal / (1 / a.mass + 1 / b.mass);

        // Apply impulse
        Vector impulse = collisionNormal.multiply(impulseLength);
        a.setVelocity(a.getVelocity().minus(impulse.multiply(1 / a.mass)));
        b.setVelocity(b.getVelocity().plus(impulse.multiply(1 / b.mass)));
    }

    private void resolveCollisions() {
        this.collisionChecker.getCollisions(objects).forEach(this::resolveCollision);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        objects.forEach(object -> object.getShape().toDrawing(canvas));
    }
}
