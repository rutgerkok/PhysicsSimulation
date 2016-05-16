package nl.rutgerkok.physicssimulation.world;

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
 * different speed. Only code in this package can modify the object, however.
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
     * {@code 1 / mass}. 0 for objects with infinite mass.
     */
    public final double invertedMass;

    private PhysicalObject(Shape shape, Vector velocity, Material material) {
        this.shape = Objects.requireNonNull(shape);
        this.velocity = Objects.requireNonNull(velocity);
        this.material = Objects.requireNonNull(material);

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
     * @param world
     *            The world we are in. Only used for calculating forces.
     */
    void advance(double deltaTime, PhysicsWorld world) {
        // Symplectic Euler - assumes constant force over deltaTime
        Vector force = world.calculateForce(this);
        Vector acceleration = force.multiply(invertedMass);
        velocity = velocity.plus(acceleration.multiply(deltaTime));
        shape = shape.moved(velocity.multiply(deltaTime));
    }

    /**
     * Gets the mass of this object. For objects with infinite weight,
     * {@link Double#MAX_VALUE} is returned.
     * 
     * @return The mass.
     */
    public double getMass() {
        if (material.density == 0) {
            return Double.MAX_VALUE;
        }
        return material.density * shape.getVolume();
    }

    /**
     * Gets the material of this object.
     *
     * @return The material.
     */
    public Material getMaterial() {
        return material;
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
     * Gets the velocity of this object.
     * 
     * @return The velocity.
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Changes the velocity of this object. This method should only be called by
     * the collision handling code.
     *
     * @param velocity
     *            The new velocity.
     */
    void setVelocity(Vector velocity) {
        this.velocity = Objects.requireNonNull(velocity);
    }

    @Override
    public String toString() {
        return "obj(" + shape + ", " + velocity + ", " + material + ")";
    }
}
