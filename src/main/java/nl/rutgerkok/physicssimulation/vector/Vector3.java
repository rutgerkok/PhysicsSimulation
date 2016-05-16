package nl.rutgerkok.physicssimulation.vector;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Represents a three-dimensional vector.
 *
 */
public final class Vector3 implements Vector {

    private final double x;
    private final double y;
    private final double z;

    Vector3(double x, double y, double z) {
        if (!Double.isFinite(x) || !Double.isFinite(y) || !Double.isFinite(z)) {
            throw new IllegalArgumentException("Invalid coords: (" + x + ", " + y + "," + z + ")");
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private Vector3 asVec3(Vector that) {
        if (that instanceof Vector3) {
            return (Vector3) that;
        }
        throw new IllegalArgumentException("Cannot use vector of another dimension. " + this + " vs " + that);
    }

    @Override
    public Vector3 divide(double scalar) {
        return vec3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector3 other = (Vector3) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
            return false;
        return true;
    }

    @Override
    public double getCoord(int i) {
        if (i == 0) {
            return x;
        }
        if (i == 1) {
            return y;
        }
        if (i == 2) {
            return z;
        }
        throw new IllegalArgumentException("Invalid coord position: " + i);
    }

    @Override
    public int getDimension() {
        return 3;
    }

    /**
     * Gets the x coord of this vector.
     * 
     * @return The x coord.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coord of this vector.
     * 
     * @return The y coord.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the z coord of this vector.
     * 
     * @return The z coord.
     */
    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Vector3 minus(Vector other) {
        Vector3 that = asVec3(other);
        return vec3(this.x - that.x, this.y - that.y, this.z - that.z);
    }

    @Override
    public Vector3 plus(Vector other) {
        Vector3 that = asVec3(other);
        return vec3(this.x + that.x, this.y + that.y, this.z + that.z);
    }

    @Override
    public String toString() {
        return "vec3(" + x + ", " + y + ", " + z + ")";
    }

}
