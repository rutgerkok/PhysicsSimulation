package nl.rutgerkok.physicssimulation.world;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.shape.Circle;
import nl.rutgerkok.physicssimulation.shape.Rectangle;
import nl.rutgerkok.physicssimulation.shape.Shape;

/**
 * An object in a physical world.
 *
 * <p>
 * Note that objects are mutable. This is more like how things happen in the
 * real world: if a car changes its speed, it's still the same car, but with a
 * different speed.
 * </p>
 */
public final class PhysicalObject {

    private Shape shape;
    private Vector2 velocity = vec2(0, 0);
    public double restitution = 1;
    public double mass = 2;

    /**
     * Creates a new object with the given shape.
     *
     * @param shape
     *            The shape of the object.
     * @return The object.
     */
    public static PhysicalObject obj(Shape shape) {
        return new PhysicalObject(shape);
    }

    private PhysicalObject(Shape shape) {
        this.shape = Objects.requireNonNull(shape);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the shape of this object.
     *
     * @return The shape.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Advances this object the given amount of time.
     * 
     * @param deltaTime
     *            The amount of time.
     */
    public void advance(double deltaTime) {
        // Symplectic Euler - assumes constant force over deltaTime
        Vector2 force = vec2(0, 0);
        Vector2 acceleration = force.divide(mass);
        velocity = velocity.plus(acceleration.multiply(deltaTime));
        shape = shape.moved(velocity.multiply(deltaTime));
    }

    public boolean collidesWith(PhysicalObject that) {
        Shape thisShape = this.shape;
        Shape thatShape = that.shape;

        // Every combination of shapes requires special handling
        // For now, circles don't collide with rectangles
        if (thisShape instanceof Circle && thatShape instanceof Circle) {
            return ((Circle) thisShape).overlapsWith((Circle) thatShape);
        }
        if (thisShape instanceof Rectangle && thatShape instanceof Rectangle) {
            return ((Rectangle) thisShape).overlapsWith((Rectangle) thatShape);
        }

        return false;
    }
}
