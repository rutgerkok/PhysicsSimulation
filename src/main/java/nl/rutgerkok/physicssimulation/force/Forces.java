package nl.rutgerkok.physicssimulation.force;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.Force;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

/**
 * Holds all the built-in forces.
 *
 */
public final class Forces {
    public static final Force GRAVITY = new Gravity();
    public static final Force ZERO = new ZeroForce();

    /**
     * Combines the given forces into one resultant force.
     * 
     * @param forces
     *            The forces.
     * @return The resultant force.
     */
    public static Force combine(Collection<Force> forces) {
        List<Force> immutableForces = new ArrayList<>(forces);
        return new Force() {

            @Override
            public Vector calculate(PhysicalObject object, PhysicsWorld world) {
                Vector result = object.getVelocity().multiply(0);

                for (Force force : immutableForces) {
                    result = result.plus(force.calculate(object, world));
                }

                return result;
            }
        };
    }

    Forces() {
        throw new RuntimeException("No instances");
    }
}
