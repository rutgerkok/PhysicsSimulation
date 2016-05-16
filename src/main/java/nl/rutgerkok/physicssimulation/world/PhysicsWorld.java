package nl.rutgerkok.physicssimulation.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;
import nl.rutgerkok.physicssimulation.shape.Sphere;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector3;

/**
 * Represents a physics world.
 *
 * <p>
 * To create a world, you need to create two lists: one containing the
 * {@link PhysicalObject objects} that are in the world, and one containing the
 * {@link Supervisor supervisors} of the world. Once a world is created, nothing
 * can be added or removed, only moved.
 * </p>
 * <p>
 * Example code to create a three-dimensional world:
 * </p>
 * 
 * <pre>
 * List&lt;{@link PhysicalObject}&gt; objects = {@link Arrays#asList(Object...) asList}(
 *         {@link PhysicalObject obj}({@link Sphere#sphere(Vector3, double) sphere}({@link Vector#vec3 vec3}(0, 1, -10), 2), vec3(0, 0, 6), Material.METAL, Forces.GRAVITY),
 *         obj(sphere(vec3(0, 0, 10), 2), vec3(0, 2, -6), Material.METAL, Forces.GRAVITY),
 *         obj(sphere(vec3(0, 20, 0), 3), vec3(0, -5, 0), Material.METAL, Forces.GRAVITY));
 * List&lt;Supervisor&gt; supervisors = asList(new CollisionSupervisor());
 * 
 * PhysicsWorld world = new PhysicsWorld(objects, supervisors);
 * </pre>
 *
 * <p>
 * For a two dimensional world, replace all the
 * {@link Vector#vec3(double, double, double) vec3} with
 * {@link Vector#vec2(double, double) vec2} and the 3D shapes (like spheres)
 * with 2D shapes like circles or rectangles.
 * </p>
 *
 * <p>
 * To actually simulate something, repeatedly call the {@link #advance(double)}
 * method.
 * </p>
 */
public final class PhysicsWorld implements Drawable, Iterable<PhysicalObject> {

    private final List<PhysicalObject> objects;
    private final List<Supervisor> supervisors;

    public PhysicsWorld(Collection<PhysicalObject> objects, Collection<Supervisor> supervisors) {
        this.objects = Collections.unmodifiableList(new ArrayList<>(objects));
        this.supervisors = Collections.unmodifiableList(new ArrayList<>(supervisors));
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
        objects.forEach(object -> object.advance(deltaTime, this));
        supervisors.forEach(supervisor -> supervisor.check(this));
    }

    @Override
    public Iterator<PhysicalObject> iterator() {
        return objects.iterator();
    }

    @Override
    public void toDrawing(Canvas canvas) {
        objects.forEach(object -> object.getShape().toDrawing(canvas));
    }
}
