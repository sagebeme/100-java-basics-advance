package com.learning;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    private final Sorter sorter = new Sorter();

    @Test
    void bubbleSortSortsAscending() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        sorter.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr);
    }

    @Test
    void bubbleSortHandlesAnAlreadySortedArray() {
        int[] arr = {1, 2, 3};
        sorter.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    void bubbleSortHandlesAnEmptyArray() {
        int[] arr = {};
        sorter.bubbleSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void quickSortSortsAscending() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        sorter.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr);
    }

    @Test
    void quickSortHandlesDuplicateValues() {
        int[] arr = {4, 2, 4, 1, 2};
        sorter.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 2, 4, 4}, arr);
    }

    @Test
    void bothSortsAgreeOnFiveHundredRandomValues() {
        Random random = new Random(7);
        int[] original = random.ints(500, -10_000, 10_000).toArray();
        int[] bubbleResult = original.clone();
        int[] quickResult = original.clone();

        sorter.bubbleSort(bubbleResult);
        sorter.quickSort(quickResult);

        assertArrayEquals(bubbleResult, quickResult);
    }
}
