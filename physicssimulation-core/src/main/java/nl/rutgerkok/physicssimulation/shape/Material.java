package nl.rutgerkok.physicssimulation.shape;

/**
 * The materials that make up a shape.
 *
 */
public enum Material {
    ROCK(0.6, 0.1),
    WOOD(0.3, 0.2),
    METAL(1.2, 0.05),
    BOUNCYBALL(0.3, 0.8),
    SUPERBALL(0.3, 0.95),
    PILLOW(0.1, 0.1),
    STATIC(0.0, 0.4);

    public enum Flag {
        GRAVITY
    }

    /**
     * The density of the material, or 0 for objects with infinite mass.
     */
    public final double density;

    /**
     * The restitution, or "bouncyness" of the material.
     */
    public final double restitution;

    private Material(double density, double restitution) {
        this.density = density;
        this.restitution = restitution;
    }

}
