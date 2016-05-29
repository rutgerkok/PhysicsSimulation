package nl.rutgerkok.physicssimulation.world;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.rutgerkok.physicssimulation.force.Forces;
import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;
import nl.rutgerkok.physicssimulation.vector.Vector;

/**
 * Represents a physics simulation.
 *
 * <p>
 * To create a new simulation, use {@link WorldBuilder}. To actually simulate
 * something, repeatedly call the {@link #advance(double)} method.
 * </p>
 */
public final class PhysicsSimulation implements Drawable, Iterable<PhysicalObject> {

    private final WorldView world;
    private final List<Supervisor> supervisors;
    final Force force;

    PhysicsSimulation(WorldBuilder worldBuilder) {
        Vector zero = worldBuilder.zero;
        if (zero == null) {
            throw new IllegalArgumentException("WorldBuilder " + worldBuilder + " has no objects");
        }
        this.world = new MultipleMaterialWorldView(zero, worldBuilder.objects);
        this.supervisors = Collections.unmodifiableList(worldBuilder.supervisors);
        this.force = Forces.combine(worldBuilder.forces);
    }

    /**
     * Advances the world by the given time step.
     *
     * <p>
     * Because of the approximated integrations happening, the following two
     * code samples are not equivalent:
     * </p>
     *
     * <ol>
     * <li>{@code advance(0.5); advance(0.5);}</li>
     * <li>{@code advance(1.0);}</li>
     * </ol>
     *
     * <p>
     * Sample 2 will run faster, but the results will be less accurate.
     * </p>
     *
     * @param deltaTime
     *            The time step.
     */
    public void advance(double deltaTime) {
        world.forEach(object -> object.advance(deltaTime, this));
        supervisors.forEach(supervisor -> supervisor.check(this));
    }

    /**
     * Calculates the force that currently work on the object.
     * 
     * @param object
     *            The object.
     * @return The force.
     */
    Vector calculateForce(PhysicalObject object) {
        return this.force.calculate(object, world);
    }

    /**
     * Gets the world of this simulation.
     * 
     * @return The world.
     */
    public WorldView getWorld() {
        return world;
    }

    @Override
    public Iterator<PhysicalObject> iterator() {
        return world.iterator();
    }

    @Override
    public void toDrawing(Canvas canvas) {
        world.forEach(object -> object.getShape().toDrawing(canvas));
    }
}
