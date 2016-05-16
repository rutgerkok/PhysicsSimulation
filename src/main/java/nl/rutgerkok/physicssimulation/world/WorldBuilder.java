package nl.rutgerkok.physicssimulation.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.rutgerkok.physicssimulation.force.Forces;
import nl.rutgerkok.physicssimulation.shape.Sphere;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector3;

/**
 * Class for creating worlds. An example is shown below:
 *
 * <pre>
 * PhysicsWorld world = WorldBuilder.newWorld()
 *         .withObject({@link PhysicalObject obj}({@link Sphere#sphere(Vector3, double) sphere}({@link Vector#vec3 vec3}(0, 1, -10), 2), vec3(0, 0, 6), Material.METAL))
 *         .withObject(obj(sphere(vec3(0, 0, 10), 2), vec3(0, 2, -6), Material.METAL))
 *         .withObject(obj(sphere(vec3(0, 20, 0), 3), vec3(0, -5, 0), Material.METAL))
 *         .withSupervisor(new CollisionSupervisor())
 *         .withForce(Forces.GRAVITY)
 *         .create();
 * </pre>
 *
 * <p>
 * For a two dimensional world, replace all the
 * {@link Vector#vec3(double, double, double) vec3} with
 * {@link Vector#vec2(double, double) vec2} and the 3D shapes (like spheres)
 * with 2D shapes like circles or rectangles.
 * </p>
 */
public final class WorldBuilder {

    /**
     * Starts the creation of a new world.
     * 
     * @return The world builder.
     */
    public static WorldBuilder newWorld() {
        return new WorldBuilder();
    }

    private final List<PhysicalObject> objects = new ArrayList<>();
    private final List<Force> forces = new ArrayList<>();
    private final List<Supervisor> supervisors = new ArrayList<>();

    private WorldBuilder() {

    }

    /**
     * Creates the world.
     *
     * @return The world.
     */
    public PhysicsWorld create() {
        return new PhysicsWorld(this.objects, this.supervisors, Forces.combine(this.forces));
    }

    /**
     * Add a force to the world. The force will be applied to all instances.
     *
     * @param force
     *            The force.
     * @return The world builder, for chaining.
     */
    public WorldBuilder withForce(Force force) {
        this.forces.add(Objects.requireNonNull(force));
        return this;
    }

    /**
     * Adds an object to the world.
     *
     * @param object
     *            The object to add.
     * @return The world builder, for chaining.
     */
    public WorldBuilder withObject(PhysicalObject object) {
        this.objects.add(Objects.requireNonNull(object));
        return this;
    }

    /**
     * Adds a supervisor to the world.
     *
     * @param supervisor
     *            The supervisor to add.
     * @return The world builder, for chaining.
     */
    public WorldBuilder withSupervisor(Supervisor supervisor) {
        this.supervisors.add(Objects.requireNonNull(supervisor));
        return this;
    }
}
