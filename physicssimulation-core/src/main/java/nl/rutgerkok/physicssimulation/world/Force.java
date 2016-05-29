package nl.rutgerkok.physicssimulation.world;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;

/**
 * Represents some physical force (as in {@code F = m a}).
 *
 */
public interface Force {

    /**
     * Calculates the force that should be applied to the given object.
     * 
     * @param object
     *            The object.
     * @param otherObjects
     *            The other objects in the world.
     * @return The force.
     */
    Vector calculate(PhysicalObject object, WorldView otherObjects);

    /**
     * Creates a new force that applies only to the given materials.
     *
     * @param first
     *            The first material.
     * @param rest
     *            The other materials, may be empty.
     * @return The force.
     */
    default Force restrictTo(Material first, Material... rest) {
        Objects.requireNonNull(first);

        if (rest.length == 0) {
            return (object, world) -> calculate(object, world.filterOnMaterial(first));
        }

        Set<Material> materialSet = EnumSet.noneOf(Material.class);
        materialSet.add(first);
        for (Material material : rest) {
            materialSet.add(material);
        }
        return (object, otherObjects) -> calculate(object, otherObjects.filterOnMaterials(materialSet));
    }
}
