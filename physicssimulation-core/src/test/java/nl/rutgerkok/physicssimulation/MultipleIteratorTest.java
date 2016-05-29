package nl.rutgerkok.physicssimulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class MultipleIteratorTest {

    @Test
    public void testBasic() {
        List<List<String>> strings = Arrays.asList(
                Arrays.asList("foo", "bar", "baz"),
                Arrays.asList("one", "two", "three"));

        MultipleIterator<String> multipleIterator = new MultipleIterator<>(strings);

        assertTrue(multipleIterator.hasNext());

        assertEquals("foo", multipleIterator.next());
        assertEquals("bar", multipleIterator.next());
        assertEquals("baz", multipleIterator.next());
        assertEquals("one", multipleIterator.next());
        assertEquals("two", multipleIterator.next());
        assertEquals("three", multipleIterator.next());

        assertFalse(multipleIterator.hasNext());
    }

    @Test
    public void testEmpty() {
        List<List<String>> strings = Collections.emptyList();

        MultipleIterator<String> multipleIterator = new MultipleIterator<>(strings);

        assertFalse(multipleIterator.hasNext());
    }

    @Test
    public void testEmptyListsInBetween() {
        List<List<String>> strings = Arrays.asList(
                Collections.emptyList(),
                Arrays.asList("foo", "bar"),
                Collections.emptyList(),
                Collections.emptyList(),
                Arrays.asList("one", "two"),
                Collections.emptyList());

        MultipleIterator<String> multipleIterator = new MultipleIterator<>(strings);

        assertTrue(multipleIterator.hasNext());

        assertEquals("foo", multipleIterator.next());
        assertEquals("bar", multipleIterator.next());
        assertEquals("one", multipleIterator.next());
        assertEquals("two", multipleIterator.next());

        assertFalse(multipleIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testOverflow() {
        List<List<String>> strings = Arrays.asList(Arrays.asList("foo"));
        MultipleIterator<String> multipleIterator = new MultipleIterator<>(strings);

        multipleIterator.next();
        multipleIterator.next();
    }

    @Test
    public void testToString() {
        List<List<String>> strings = Collections.emptyList();

        MultipleIterator<String> multipleIterator = new MultipleIterator<>(strings);

        assertNotNull(multipleIterator.toString());
    }
}
