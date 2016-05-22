package nl.rutgerkok.physicssimulation.collision;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.vector.Vector;

import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Represents a collision. Two collisions are equal if they involve the same
 * objects.
 *
 */
final class Collision {
    private final PhysicalObject one;
    private final PhysicalObject other;
    private final double penetration;
    private final Vector normal;

    Collision(PhysicalObject a, PhysicalObject b, double penetration, Vector normal) {
        if (!Double.isFinite(penetration) || penetration <= 0) {
            throw new IllegalArgumentException("Invalid penetration for collision: " + penetration);
        }

        this.one = Objects.requireNonNull(a);
        this.other = Objects.requireNonNull(b);
        this.penetration = penetration;
        this.normal = Objects.requireNonNull(normal);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Collision))
            return false;
        Collision that = (Collision) obj;

        if (that.one.equals(this.one) && that.other.equals(this.other)) {
            return true;
        }
        if (that.one.equals(this.other) && that.other.equals(this.one)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the collision normal.
     * 
     * @return The collision normal.
     */
    Vector getNormal() {
        return normal;
    }

    /**
     * Gets one of the object involved in this collision.
     * 
     * @return One of the objects.
     */
    PhysicalObject getOneObject() {
        return one;
    }


    /**
     * Gets the other object involved in this collision.
     * 
     * @return The other object.
     */
    PhysicalObject getOtherObject() {
        return other;
    }

    /**
     * Gets how deep the objects are inside of each other.
     * 
     * @return The penetration.
     */
    double getPenetration() {
        return penetration;
    }

    @Override
    public int hashCode() {
        return one.hashCode() + other.hashCode();
    }

    @Override
    public String toString() {
        return "new Collision(" + one + ", " + other + ", " + penetration + ", " + normal + ")";
    }
}