package algorithms.stack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E> {
    @Override
    public void push(@NotNull E item) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public E pop() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int size() {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        throw new RuntimeException("Not implemented");
    }
}
