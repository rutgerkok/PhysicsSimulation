package nl.rutgerkok.physicssimulation.world;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.shape.Shape;
import nl.rutgerkok.physicssimulation.vector.Vector;

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
     * @param material
     *            The material of the object.
     * @return The object.
     */
    public static PhysicalObject obj(Shape shape, Vector velocity, Material material) {
        return new PhysicalObject(shape, velocity, material);
    }

    private Shape shape;
    private Vector velocity;
    private final Material material;

    /**
     * The restitution, or "bouncyness" of the object.
     */
    public final double restitution;
    /**
     * {@code 1 / mass}. 0 for objects with infinite mass.
     */
    public final double invertedMass;

    private PhysicalObject(Shape shape, Vector velocity, Material material) {
        this.shape = Objects.requireNonNull(shape);
        this.velocity = Objects.requireNonNull(velocity);
        this.material = Objects.requireNonNull(material);

        this.restitution = material.restitution;
        if (material.density == 0) {
            this.invertedMass = 0;
        } else {
            double mass = material.density * shape.getVolume();
            this.invertedMass = 1 / mass;
        }
    }

    /**
     * Advances this object the given amount of time.
     * 
     * @param deltaTime
     *            The amount of time.
     */
    public void advance(double deltaTime) {
        // Symplectic Euler - assumes constant force over deltaTime
        Vector force = vec2(0, 0);
        Vector acceleration = force.multiply(invertedMass);
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

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "obj(" + shape + ", " + velocity + ", " + material + ")";
    }
}
