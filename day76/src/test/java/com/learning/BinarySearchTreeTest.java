package com.learning;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void findsAValueThatWasInserted() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);
        assertTrue(tree.contains(3));
    }

    @Test
    void doesNotFindAValueThatWasNeverInserted() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        assertFalse(tree.contains(99));
    }

    @Test
    void inOrderTraversalVisitsValuesInSortedOrder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int value : new int[]{5, 3, 8, 1, 4, 7, 9}) {
            tree.insert(value);
        }
        assertEquals(List.of(1, 3, 4, 5, 7, 8, 9), tree.inOrder());
    }

    @Test
    void insertingADuplicateDoesNotIncreaseSize() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(5);
        assertEquals(1, tree.size());
    }

    @Test
    void heightOfAnEmptyTreeIsZero() {
        assertEquals(0, new BinarySearchTree<Integer>().height());
    }

    @Test
    void aBalancedInsertOrderKeepsTheTreeShallow() {
        // Inserting in this order keeps the tree roughly balanced, height should stay small.
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int value : new int[]{50, 25, 75, 12, 37, 62, 87}) {
            tree.insert(value);
        }
        assertEquals(3, tree.height());
    }

    @Test
    void handlesTenThousandRandomInsertsAndFindsEveryOneOfThem() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Random random = new Random(42);
        java.util.Set<Integer> inserted = new java.util.HashSet<>();

        while (inserted.size() < 10_000) {
            int value = random.nextInt(1_000_000);
            tree.insert(value);
            inserted.add(value);
        }

        assertEquals(inserted.size(), tree.size());
        for (int value : inserted) {
            assertTrue(tree.contains(value));
        }
    }
}
