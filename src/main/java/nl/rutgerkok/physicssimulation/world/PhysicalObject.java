package nl.rutgerkok.physicssimulation.world;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
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

    /**
     * Creates a new object with the given shape.
     *
     * @param shape
     *            The shape of the object.
     * @param velocity
     *            The velocity of the object.
     * @return The object.
     */
    public static PhysicalObject obj(Shape shape, Vector2 velocity) {
        return new PhysicalObject(shape, velocity);
    }

    private Shape shape;
    private Vector2 velocity;
    public double restitution = 1;

    public double mass = 2;

    private PhysicalObject(Shape shape, Vector2 velocity) {
        this.shape = Objects.requireNonNull(shape);
        this.velocity = Objects.requireNonNull(velocity);
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

    /**
     * Gets the shape of this object.
     *
     * @return The shape.
     */
    public Shape getShape() {
        return shape;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "obj(" + shape + ", " + velocity + ")";
    }
}
