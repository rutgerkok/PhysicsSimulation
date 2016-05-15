package nl.rutgerkok.physicssimulation;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.paint.Drawable;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A 2-dimensional immutable vector.
 *
 */
public final class Vector implements Drawable {

    /**
     * Creates an N-dimensional vector with the given coords.
     * 
     * @param coords
     *            The coords.
     * @return The vector.
     * @see #vec2(double, double)
     * @see #vec3(double, double, double)
     */
    public static Vector vec(double[] coords) {
        return new Vector(Arrays.copyOf(coords, coords.length));
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
    public static Vector vec2(double x, double y) {
        return new Vector(new double[] { x, y });
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
    public static Vector vec3(double x, double y, double z) {
        return new Vector(new double[] { x, y, z });
    }

    private final double[] coords;

    private Vector(double[] coords) {
        this.coords = Objects.requireNonNull(coords);
        for (double coord : coords) {
            if (!Double.isFinite(coord)) {
                String coordString = Arrays.stream(coords)
                        .mapToObj(Double::toString)
                        .collect(Collectors.joining(", "));
                throw new IllegalArgumentException("Invalid coords: (" + coordString + ")");
            }
        }
    }

    /**
     * Checks if the vector has the required dimension. This is useful when
     * validating parameters.
     * 
     * @param dimension
     *            The required dimension.
     * @throws IllegalArgumentException
     *             If this vector has another dimension.
     */
    public void checkDimension(int dimension) {
        if (dimension != this.coords.length) {
            throw new IllegalArgumentException("A " + dimension + "D vector is"
                    + " required, but a " + this.coords.length + "D vector was given");
        }
    }

    private void checkMinimumDimension(int i) {
        if (i > this.coords.length) {
            throw new UnsupportedOperationException("This is a " + this.coords.length + "D vector");
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
    public Vector divide(double scalar) {
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
    public double dotProduct(Vector that) {
        checkDimension(that.coords.length);
        double sum = 0;
        for (int i = 0; i < this.coords.length; i++) {
            sum += this.coords[i] * that.coords[i];
        }
        return sum;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector))
            return false;
        Vector other = (Vector) obj;
        if (!Arrays.equals(this.coords, other.coords)) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the distance to the other vector.
     *
     * @param that
     *            The other vector.
     * @return The distance.
     */
    public double getDistanceTo(Vector that) {
        return Math.sqrt(getSquaredDistanceTo(that));
    }

    /**
     * Gets the length of this vector.
     * 
     * @return The length.
     * @see #getSquaredLength() Faster method
     */
    public double getLength() {
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
    public double getSquaredDistanceTo(Vector that) {
        Vector difference = this.minus(that);
        return difference.dotProduct(difference);
    }

    /**
     * Gets the squared length of this vector. Unlike {@link #getLength()}, this
     * method doesn't need to calculate a costly square root.
     * 
     * @return The squared length.
     */
    public double getSquaredLength() {
        return this.dotProduct(this);
    }

    /**
     * Gets the x position of this vector.
     * 
     * @return The x position.
     * @throws UnsupportedOperationException
     *             For 0D vectors.
     */
    public double getX() {
        checkMinimumDimension(1);
        return coords[0];
    }

    /**
     * Gets the y position of this vector.
     * 
     * @return The y position.
     * @throws UnsupportedOperationException
     *             For 1D or lower vectors.
     */
    public double getY() {
        checkMinimumDimension(2);
        return coords[1];
    }

    /**
     * Gets the y position of this vector.
     * 
     * @return The y position.
     * @throws UnsupportedOperationException
     *             For 2D or lower vectors.
     */
    public double getZ() {
        checkMinimumDimension(3);
        return coords[2];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    /**
     * Subtracts another vector from this vector, and returns the result.
     *
     * @param that
     *            The other vector.
     * @return The result.
     */
    public Vector minus(Vector that) {
        checkDimension(that.coords.length);
        double[] newArray = new double[coords.length];
        for (int i = 0; i < this.coords.length; i++) {
            newArray[i] = this.coords[i] - that.coords[i];
        }
        return new Vector(newArray);
    }

    /**
     * Multiplies this vector with a scalar.
     * 
     * @param scalar
     *            The scalar.
     * @return The multiplied vector.
     */
    public Vector multiply(double scalar) {
        double[] newArray = new double[coords.length];
        for (int i = 0; i < this.coords.length; i++) {
            newArray[i] = this.coords[i] * scalar;
        }
        return new Vector(newArray);
    }

    /**
     * Gets a normalized version of this vector, i.e. a vector with the same
     * direction, but a different magnitude.
     * 
     * @return The normalized version.
     */
    public Vector normalized() {
        return this.divide(this.getLength());
    }

    /**
     * Adds another vector to this vector.
     * 
     * @param that
     *            The other vector.
     * @return The result vector.
     */
    public Vector plus(Vector that) {
        checkDimension(that.coords.length);
        double[] newArray = new double[coords.length];
        for (int i = 0; i < this.coords.length; i++) {
            newArray[i] = this.coords[i] + that.coords[i];
        }
        return new Vector(newArray);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        // Draw a cross
        canvas.drawLine(this.plus(vec2(-2, -2)), this.plus(vec2(2, 2)));
        canvas.drawLine(this.plus(vec2(2, -2)), this.plus(vec2(-2, 2)));
    }

    @Override
    public String toString() {
        String coordString = Arrays.stream(coords)
                .map(coord -> Math.round(coord * 100) / 100)
                .mapToObj(Double::toString)
                .collect(Collectors.joining(", "));
        return "vec" + this.coords.length + "(" + coordString + ")";
    }

}
