package nl.rutgerkok.physicssimulation;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Flattens nested {@link Iterable}s into a single iterator. Elements cannot be
 * removed using this iterator.
 *
 * @param <T>
 *            The type of the objects inside.
 */
public final class MultipleIterator<T> implements Iterator<T> {
    private Iterator<? extends Iterable<T>> iterators;
    private Iterator<T> currentIterator = Collections.emptyIterator();;

    public MultipleIterator(Iterable<? extends Iterable<T>> iterables) {
        this.iterators = iterables.iterator();
    }

    @Override
    public boolean hasNext() {
        if (currentIterator.hasNext()) {
            // Elements remain on current iterator
            return true;
        }

        // Current iterator is exhausted

        if (!iterators.hasNext()) {
            // This was the last iterator
            return false;
        }

        // Switch to next iterator
        currentIterator = iterators.next().iterator();
        return hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }

    @Override
    public String toString() {
        return MultipleIterator.class.getSimpleName();
    }

}