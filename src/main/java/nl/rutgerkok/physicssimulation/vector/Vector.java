package nl.rutgerkok.physicssimulation.vector;

import java.util.Objects;

/**
 * Represents a vector in an unknown amount of dimensions.
 */
public interface Vector {

    /**
     * Creates a vector of two or three dimensions.
     *
     * @param coords
     *            The coords.
     * @return The vector.
     * @throws IllegalArgumentException
     *             When the amount of given coords is not two or three.
     */
    static Vector vec(double[] coords) {
        if (coords.length == 2) {
            return vec2(coords[0], coords[1]);
        }
        if (coords.length == 3) {
            return vec3(coords[0], coords[1], coords[2]);
        }
        throw new IllegalArgumentException("Dimension not supported: " + coords.length);
    }

    /**
     * Creates a 2D vector with the given x and y.
     * 
     * @param x
     *            The x.
     * @param y
     *            The y.
     * @return The vector.
     * @throws IllegalArgumentException
     *             If the x or y are infinite or NaN.
     */
    static Vector2 vec2(double x, double y) {
        return new Vector2(x, y);
    }

    /**
     * Creates a 3D vector with the given x, y and z.
     * 
     * @param x
     *            The x.
     * @param y
     *            The y.
     * @param z
     *            The z.
     * @return The vector.
     * @throws IllegalArgumentException
     *             If the x or y are infinite or NaN.
     */
    static Vector3 vec3(double x, double y, double z) {
        return new Vector3(x, y, z);
    }

    /**
     * Checks if the given vector has the same dimensions as this vector.
     * 
     * @param that
     *            The given vector.
     * @throws IllegalArgumentException
     *             If the given vector has another dimension.
     */
    default void checkSameDimension(Vector that) {
        Objects.requireNonNull(that);
        if (that.getDimension() != this.getDimension()) {
            throw new IllegalArgumentException(
                    "dimension mismatch: " + this.getDimension() + " vs " + that.getDimension());
        }
    }

    /**
     * Divides this vector with a scalar.
     *
     * @param scalar
     *            The scalar.
     * @return The divided vector.
     * @throws IllegalArgumentException
     *             On division by 0.
     */
    default Vector divide(double scalar) {
        return multiply(1 / scalar);
    }

    /**
     * Gets the dot product between this vector and the other.
     * 
     * @param that
     *            The other vector.
     * @return The dot product.
     * @throws IllegalArgumentException
     *             When the other vector has a different dimension.
     */
    default double dotProduct(Vector that) {
        checkSameDimension(that);
        int dimension = this.getDimension();
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += this.getCoord(i) * that.getCoord(i);
        }
        return sum;
    }

    /**
     * Gets the ith coord.
     * 
     * @param i
     *            The coord position.
     * @throws IllegalArgumentException
     *             If i &gt;= dimension.
     * @return The coord.
     */
    double getCoord(int i);

    /**
     * Gets the dimension of this vector (usually 2 or 3).
     * 
     * @return The dimension.
     */
    int getDimension();

    /**
     * Calculates the distance to the other vector.
     *
     * @param that
     *            The other vector.
     * @return The distance.
     */
    default double getDistanceTo(Vector that) {
        return Math.sqrt(getSquaredDistanceTo(that));
    }

    /**
     * Gets the length of this vector.
     * 
     * @return The length.
     * @see #getSquaredLength() Faster method
     */
    default double getLength() {
        return Math.sqrt(this.dotProduct(this));
    }

    /**
     * Calculates the squared distance to the other vector. Calculating the
     * squared distance is faster than calculating the distance, as we don't
     * have to calculate a square root.
     *
     * @param that
     *            The other vector.
     * @return The distance.
     */
    default double getSquaredDistanceTo(Vector that) {
        Vector difference = this.minus(that);
        return difference.dotProduct(difference);
    }

    /**
     * Gets the squared length of this vector. Unlike {@link #getLength()}, this
     * method doesn't need to calculate a costly square root.
     * 
     * @return The squared length.
     */
    default double getSquaredLength() {
        return this.dotProduct(this);
    }

    /**
     * Subtracts another vector from this vector, and returns the result.
     *
     * @param that
     *            The other vector.
     * @return The result.
     */
    Vector minus(Vector that);

    /**
     * Multiplies this vector with a scalar.
     * 
     * @param scalar
     *            The scalar.
     * @return The multiplied vector.
     */
    default Vector multiply(double scalar) {
        double[] newArray = new double[getDimension()];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = this.getCoord(i) * scalar;
        }
        return vec(newArray);
    }

    /**
     * Gets a normalized version of this vector, i.e. a vector with the same
     * direction, but a different magnitude.
     * 
     * @return The normalized version.
     */
    default Vector normalized() {
        return this.divide(this.getLength());
    }

    /**
     * Adds another vector to this vector.
     * 
     * @param that
     *            The other vector.
     * @return The result vector.
     */
    Vector plus(Vector that);

    /**
     * Gets a new vector of the same dimension with the value at the given
     * position changed.
     *
     * @param position
     *            The position.
     * @param value
     *            The new value.
     * @return The new vector.
     * @throws IllegalArgumentException
     *             If {@code position < 0 || position >= getDimension()}.
     * @throws IllegalArgumentException
     *             IF the value is not finite.
     */
    Vector withCoord(int position, double value);

}
