package nl.rutgerkok.physicssimulation.collision;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;

/**
 * Represents a collision. Two collisions are equal if they involve the same
 * objects.
 *
 */
public final class Collision {
    private final PhysicalObject one;
    private final PhysicalObject other;
    private final double penetration;
    private final Vector2 normal;

    Collision(PhysicalObject a, PhysicalObject b, double penetration, Vector2 normal) {
        if (!Double.isFinite(penetration) || penetration <= 0) {
            throw new IllegalArgumentException("Invalid penetration for collision: " + penetration);
        }

        this.one = Objects.requireNonNull(a);
        this.other = Objects.requireNonNull(b);
        this.penetration = penetration;
        this.normal = Objects.requireNonNull(normal);
    }

    @Override
    public boolean equals(Object obj) {
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
    public Vector2 getNormal() {
        return normal;
    }

    /**
     * Gets one of the object involved in this collision.
     * 
     * @return One of the objects.
     */
    public PhysicalObject getOneObject() {
        return one;
    }

    /**
     * Gets the other object involved in this collision.
     * 
     * @return The other object.
     */
    public PhysicalObject getOtherObject() {
        return other;
    }

    /**
     * Gets how deep the objects are inside of each other.
     * 
     * @return The penetration.
     */
    public double getPenetration() {
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