package com.learning;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomQueueTest {

    @Test
    void dequeuesInFirstInFirstOutOrder() {
        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void peekDoesNotRemoveTheFrontElement() {
        CustomQueue<String> queue = new CustomQueue<>();
        queue.enqueue("a");
        assertEquals("a", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    void dequeuingAnEmptyQueueThrows() {
        assertThrows(NoSuchElementException.class, () -> new CustomQueue<Integer>().dequeue());
    }

    @Test
    void handlesTenThousandEnqueuesAndDequeuesCorrectly() {
        CustomQueue<Integer> queue = new CustomQueue<>();
        for (int i = 0; i < 10_000; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertEquals(i, queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }
}
