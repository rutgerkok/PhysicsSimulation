package nl.rutgerkok.physicssimulation.world;

import static java.util.Arrays.asList;
import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import nl.rutgerkok.physicssimulation.shape.Material;

import org.junit.Test;

/**
 * Tests on all implementations of {@link WorldView}.
 *
 */
public class WorldViewTest {

    private static void testSingleElementIterator(WorldView world, PhysicalObject singleElement) {
        Iterator<PhysicalObject> it = world.iterator();
        assertTrue(it.hasNext());
        assertEquals(singleElement, it.next());
        assertFalse(it.hasNext());

        try {
            it.next();
            fail("There is no next element");
        } catch (NoSuchElementException e) {

        }
    }

    @Test
    public void testContains() {
        // Test whether contains is well-behaved
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.ROCK);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));

        assertTrue(world.contains(circle1));
        assertTrue(world.contains(circle2));
        assertFalse(world.contains(null));
    }

    @Test
    public void testDoubleFilter() {
        // Test the effect on the size of filtering a world twice
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView onlyRocks = world.filterOnMaterial(Material.ROCK);

        WorldView doubleFiltered = onlyRocks.filterOnMaterial(Material.ROCK);
        WorldView impossibleFilter = onlyRocks.filterOnMaterial(Material.PILLOW);
        WorldView difficultDoubleFilter = onlyRocks.filterOnMaterials(EnumSet.of(Material.METAL, Material.ROCK));
        WorldView impossibleDifficultFilter = onlyRocks
                .filterOnMaterials(EnumSet.of(Material.SUPERBALL, Material.PILLOW));

        assertEquals(1, onlyRocks.size());
        assertEquals(1, doubleFiltered.size());
        assertEquals(0, impossibleFilter.size());
        assertEquals(1, difficultDoubleFilter.size());
        assertEquals(0, impossibleDifficultFilter.size());
    }

    @Test
    public void testEmptyWorld() {
        // Test empty worlds: what is their size, do they filter correctly?
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView empty = world.filterOnMaterials(EnumSet.of(Material.SUPERBALL, Material.METAL));

        assertEquals(0, empty.size());
        assertEquals(0, empty.filterOnMaterial(Material.ROCK).size());
        assertEquals(0, empty.filterOnMaterials(EnumSet.of(Material.ROCK, Material.PILLOW)).size());

        assertFalse(empty.iterator().hasNext());
    }

    @Test
    public void testFilteredContains() {
        // Test if contains is well-behaved
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView pillowFiltered = world.filterOnMaterial(Material.PILLOW);
        WorldView superBallFiltered = world.filterOnMaterial(Material.SUPERBALL);

        assertFalse(pillowFiltered.contains(circle1));
        assertTrue(pillowFiltered.contains(circle2));

        assertFalse(superBallFiltered.contains(circle1));
        assertFalse(superBallFiltered.contains(circle2));
    }

    @Test
    public void testIterator() {
        // Test whether iterator is well-behaved
        PhysicalObject circle = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle));
        WorldView onlyRocks = world.filterOnMaterial(Material.ROCK);

        testSingleElementIterator(world, circle);
        testSingleElementIterator(onlyRocks, circle);
    }

    @Test
    public void testMultipleMaterialFilter() {
        // Test whether filters of multiple materials work
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView filtered = world.filterOnMaterials(EnumSet.of(Material.ROCK, Material.PILLOW, Material.WOOD));

        assertEquals(2, filtered.size());
    }

    @Test
    public void testSize() {
        // Test whether size is accurate
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView pillowFiltered = world.filterOnMaterial(Material.PILLOW);
        WorldView superBallFiltered = world.filterOnMaterial(Material.SUPERBALL);

        assertEquals(2, world.size());
        assertEquals(1, pillowFiltered.size());
        assertEquals(0, superBallFiltered.size());
    }

    @Test
    public void testToArray() {
        PhysicalObject circle = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle));

        assertArrayEquals(new Object[] { circle }, world.toArray());
        assertArrayEquals(new PhysicalObject[] { circle }, world.toArray(new PhysicalObject[1]));
    }

    @Test(expected = NullPointerException.class)
    public void testToNullArray() {
        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), Collections.emptyList());

        world.toArray(null);
    }

    @Test
    public void testZero() {
        // Test retrieval of zero vector
        PhysicalObject circle1 = obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK);
        PhysicalObject circle2 = obj(circle(vec2(3, 0), 2), vec2(0, 0), Material.PILLOW);

        WorldView world = new MultipleMaterialWorldView(vec2(0, 0), asList(circle1, circle2));
        WorldView filtered1 = world.filterOnMaterial(Material.WOOD);
        WorldView filtered2 = world.filterOnMaterial(Material.ROCK);

        assertEquals(vec2(0, 0), world.getZeroVector());
        assertEquals(vec2(0, 0), filtered1.getZeroVector());
        assertEquals(vec2(0, 0), filtered2.getZeroVector());
    }
}
