package com.learning;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CustomQueue<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    public void enqueue(T element) {
        elements.addLast(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.getFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}
