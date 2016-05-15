package nl.rutgerkok.physicssimulation.shape;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;

import java.util.Objects;

import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.paint.Canvas;

/**
 * Represents a circle.
 *
 */
public final class Circle implements Shape {
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
    public static Circle circle(Vector2 center, double radius) {
        if (!Double.isFinite(radius)) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }

        return new Circle(center, radius);
    }

    private final double radius;

    private final Vector2 center;

    private Circle(Vector2 center, double radius) {
        this.center = Objects.requireNonNull(center);
        this.radius = radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circle other = (Circle) obj;
        if (!center.equals(other.center))
            return false;
        if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
            return false;
        return true;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public Vector2 getCenter() {
        return center;
    }

    /**
     * Gets the radius of this circle.
     * 
     * @return The radius.
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((center == null) ? 0 : center.hashCode());
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Shape moved(Vector2 amount) {
        return circle(center.plus(amount), radius);
    }

    @Override
    public void toDrawing(Canvas canvas) {
        canvas.drawArc(center.plus(vec2(-radius, -radius)), vec2(radius * 2, radius * 2), 0, 2 * Math.PI);
    }

    @Override
    public String toString() {
        return "circle(" + center + ", " + radius + ")";
    }
}
