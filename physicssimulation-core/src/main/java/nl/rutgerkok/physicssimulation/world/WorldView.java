package nl.rutgerkok.physicssimulation.world;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Contains the world. The view may not show everything of the world, to prevent
 * certain objects from interacting, for example.
 *
 * <p>
 * The world behaves as an immutable collection of {@link PhysicalObject}s.
 */
public interface WorldView extends Collection<PhysicalObject> {

    @Override
    @Deprecated
    public default boolean add(PhysicalObject e) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public default boolean addAll(@Nullable Collection<? extends PhysicalObject> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public default boolean containsAll(@Nullable Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new {@link WorldView} that only contains objects of the given
     * type.
     *
     * @param material
     *            The material to filter on.
     * @return The collection.
     */
    WorldView filterOnMaterial(Material material);

    /**
     * Creates a new {@link WorldView} that only contains objects of the given
     * types.
     *
     * @param materials
     *            The materials to filter on.
     * @return The collection.
     */
    WorldView filterOnMaterials(Set<Material> materials);

    /**
     * Gets the 0-vector in the correct dimension for this world.
     * 
     * @return The 0-vector.
     */
    Vector getZeroVector();

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    @Deprecated
    public default boolean remove(@Nullable Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public default boolean removeAll(@Nullable Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public default boolean retainAll(@Nullable Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default Object[] toArray() {
        return toArray(new Object[0]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public default <T> T[] toArray(T @Nullable [] param) {
        if (param == null) {
            // Required to keep Eclipse's null checker happy
            throw new NullPointerException();
        }

        T[] array = param;
        int size = size();
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        int i = 0;
        for (PhysicalObject object : this) {
            array[i] = (T) object;
            i++;
        }
        return array;
    }

}
