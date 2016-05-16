package nl.rutgerkok.physicssimulation.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

/**
 * Represents a physics world.
 *
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
