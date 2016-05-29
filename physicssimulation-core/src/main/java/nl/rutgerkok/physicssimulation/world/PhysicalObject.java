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
     * Represents what's happening in a step. First, the a velocity is given,
     * and this is used to calculate where the shape will be at the end of the
     * step. Then the {@link Supervisor}s come in and change the velocity. This
     * means that the calculated end position is no longer accurate. However, as
     * to not disturb the collision mechanisms, it is not recalculated until the
     * step is over.
     *
     */
    private static class CurrentStep {
        private final Shape shapeAtBeginning;
        /**
         * The shape at the end of the step, as predicted by the initial
         * velocity.
         */
        private Shape predictedEndShape;
        private boolean isPredictionAccurate;
        private final double deltaTime;
        private Vector velocity;

        public CurrentStep(Shape shapeAtBeginning, Vector velocity, double deltaTime) {
            this.shapeAtBeginning = Objects.requireNonNull(shapeAtBeginning);
            this.isPredictionAccurate = true;
            this.velocity = Objects.requireNonNull(velocity);
            this.deltaTime = deltaTime;

            this.predictedEndShape = shapeAtBeginning.moved(velocity.multiply(deltaTime));
        }

        /**
         * Gets the final, resulting shape using the current velocity.
         * 
         * @return The final shape.
         */
        Shape getResultingShape() {
            if (this.isPredictionAccurate) {
                return this.predictedEndShape;
            }
            // Recalculate using changed velocity
            return this.shapeAtBeginning.moved(velocity.multiply(deltaTime));
        }

        void replaceVelocity(Vector velocity) {
            // Oh - the velocity is replaced. Our prediction is no longer
            // accurate. Mark is as such.
            this.velocity = Objects.requireNonNull(velocity);
            this.isPredictionAccurate = false;
        }
    }

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

    private CurrentStep currentStep;
    private final Material material;

    /**
     * {@code 1 / mass}. 0 for objects with infinite mass.
     */
    public final double invertedMass;

    private PhysicalObject(Shape shape, Vector velocity, Material material) {
        this.material = Objects.requireNonNull(material);

        this.currentStep = new CurrentStep(shape, velocity, 0);

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
    void advance(double deltaTime, PhysicsSimulation world) {
        // Symplectic Euler - assumes constant force over deltaTime
        Vector force = world.calculateForce(this);
        Vector acceleration = force.multiply(invertedMass);
        Vector velocity = currentStep.velocity.plus(acceleration.multiply(deltaTime));

        currentStep = new CurrentStep(currentStep.getResultingShape(), velocity, deltaTime);
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
        return material.density * currentStep.predictedEndShape.getVolume();
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
        return currentStep.predictedEndShape;
    }

    /**
     * Gets the velocity of this object.
     * 
     * @return The velocity.
     */
    public Vector getVelocity() {
        return currentStep.velocity;
    }

    /**
     * Replaces the velocity of this object. The movements of the lastest time
     * step will be undone and recalculated later on using this velocity. This
     * method should only be called by the collision handling code.
     *
     * @param velocity
     *            The new velocity.
     */
    void replaceVelocity(Vector velocity) {
        currentStep.replaceVelocity(velocity);
    }

    @Override
    public String toString() {
        return "obj(" + currentStep.predictedEndShape + ", " + currentStep.velocity + ", " + material + ")";
    }
}
