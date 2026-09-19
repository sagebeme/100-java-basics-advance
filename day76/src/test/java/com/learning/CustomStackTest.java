package com.learning;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class CustomStackTest {

    @Test
    void popsInLastInFirstOutOrder() {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void peekDoesNotRemoveTheTopElement() {
        CustomStack<String> stack = new CustomStack<>();
        stack.push("a");
        assertEquals("a", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void poppingAnEmptyStackThrows() {
        assertThrows(EmptyStackException.class, () -> new CustomStack<Integer>().pop());
    }

    @Test
    void handlesTenThousandPushesAndPopsCorrectly() {
        CustomStack<Integer> stack = new CustomStack<>();
        for (int i = 0; i < 10_000; i++) {
            stack.push(i);
        }
        assertEquals(10_000, stack.size());
        for (int i = 9_999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }
}
