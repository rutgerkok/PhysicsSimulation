package nl.rutgerkok.physicssimulation.vector;

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
    public String toString() {
        return "vec3(" + x + ", " + y + "," + z + ")";
    }

}
