package nl.rutgerkok.physicssimulation.world;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import nl.rutgerkok.physicssimulation.MultipleIterator;
import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.vector.Vector;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A view of all objects in the world.
 *
 */
class MultipleMaterialWorldView implements WorldView {

    private class SingleMaterialWorldView implements WorldView {

        private final PhysicalObject[] objects;
        private final Material material;

        SingleMaterialWorldView(Material material, List<PhysicalObject> objects) {
            this.material = Objects.requireNonNull(material);
            this.objects = objects.toArray(new PhysicalObject[0]);
        }

        @Override
        public boolean contains(@Nullable Object object) {
            for (PhysicalObject compareWith : objects) {
                if (compareWith.equals(object)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public WorldView filterOnMaterial(Material material) {
            if (material == this.material) {
                return this;
            }
            return emptyWorldView;
        }

        @Override
        public WorldView filterOnMaterials(Set<Material> materials) {
            if (materials.contains(this.material)) {
                return this;
            }
            return emptyWorldView;
        }

        @Override
        public Vector getZeroVector() {
            return zero;
        }

        @Override
        public Iterator<PhysicalObject> iterator() {
            return new Iterator<PhysicalObject>() {

                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < objects.length;
                }

                @Override
                public PhysicalObject next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    PhysicalObject object = objects[i];
                    i++;
                    return object;
                }
            };
        }

        @Override
        public int size() {
            return objects.length;
        }

    }

    private final WorldView emptyWorldView = new WorldView() {

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @Override
        public WorldView filterOnMaterial(Material material) {
            return this;
        }

        @Override
        public WorldView filterOnMaterials(Set<Material> materials) {
            return this;
        }

        @Override
        public Vector getZeroVector() {
            return zero;
        }

        @Override
        public Iterator<PhysicalObject> iterator() {
            return Collections.emptyIterator();
        }

        @Override
        public int size() {
            return 0;
        }
    };

    /**
     * All objects, grouped by their material.
     */
    private final Map<Material, WorldView> objects;

    /**
     * The zero vector in the correct dimension.
     */
    private final Vector zero;

    MultipleMaterialWorldView(Vector zero, Collection<PhysicalObject> objects) {
        this.zero = Objects.requireNonNull(zero);

        Map<Material, List<PhysicalObject>> groupedObjects = objects.stream()
                .collect(Collectors.groupingBy(
                        PhysicalObject::getMaterial,
                        () -> new EnumMap<>(Material.class),
                        Collectors.toList()));
        this.objects = new HashMap<>();
        groupedObjects.forEach((material, objectsWithMaterial) -> {
            this.objects.put(material, new SingleMaterialWorldView(material, objectsWithMaterial));
        });
    }

    private MultipleMaterialWorldView(Vector zero, Map<Material, WorldView> objects) {
        this.zero = Objects.requireNonNull(zero);
        this.objects = Objects.requireNonNull(objects);
    }

    @Override
    public boolean contains(@Nullable Object o) {
        if (!(o instanceof PhysicalObject)) {
            return false;
        }
        PhysicalObject object = (PhysicalObject) o;
        Material material = object.getMaterial();

        // Only look in the relevant bucket
        return filterOnMaterial(material).contains(object);
    }

    @Override
    public WorldView filterOnMaterial(Material material) {
        return this.objects.getOrDefault(material, emptyWorldView);
    }

    @Override
    public WorldView filterOnMaterials(Set<Material> materials) {
        Map<Material, WorldView> result = new EnumMap<>(Material.class);
        objects.forEach((material, objects) -> {
            if (materials.contains(material)) {
                // Include this subset
                result.put(material, objects);
            }
        });
        if (result.isEmpty()) {
            return emptyWorldView;
        }
        return new MultipleMaterialWorldView(zero, result);
    }

    @Override
    public Vector getZeroVector() {
        return zero;
    }

    @Override
    public Iterator<PhysicalObject> iterator() {
        return new MultipleIterator<>(objects.values());
    }

    @Override
    public int size() {
        return objects.values().stream().mapToInt(WorldView::size).sum();
    }

}
