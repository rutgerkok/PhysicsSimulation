package nl.rutgerkok.physicssimulation.shape;

import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.paint.Canvas;
import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.vector.Vector3;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Represents a three-dimensional sphere.
 *
 */
public final class Sphere implements Spherical {
    /**
     * Creates a new circle with the given center and radius.
     * 
     * @param center
     *            Center of the circle.
     * @param radius
     *            Radius of the circle.
     * @return The circle.
     * @throws IllegalArgumentException
     *             If the radius is not a finite number.
     */
    public static Sphere sphere(Vector3 center, double radius) {
        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }

        return new Sphere(center, radius);
    }

    private final double radius;
    private final Vector3 center;

    private Sphere(Vector3 center, double radius) {
        this.center = Objects.requireNonNull(center);
        this.radius = radius;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sphere other = (Sphere) obj;
        if (!center.equals(other.center))
            return false;
        if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
            return false;
        return true;
    }

    @Override
    public Vector3 getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * (radius * radius * radius);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + center.hashCode();
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Shape moved(Vector amount) {
        return sphere(center.plus(amount), radius);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        Vector3 radiusSquare = vec3(radius, radius, radius);
        canvas.drawEgg(center.minus(radiusSquare), center.plus(radiusSquare));
    }

    @Override
    public String toString() {
        return "sphere(" + center + ", " + radius + ")";
    }
}
