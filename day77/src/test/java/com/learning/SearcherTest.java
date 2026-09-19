package com.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {

    private final Searcher searcher = new Searcher();
    private final int[] sorted = {1, 3, 5, 7, 9, 11};

    @Test
    void findsAValueInTheMiddle() {
        assertEquals(3, searcher.binarySearch(sorted, 7));
    }

    @Test
    void findsTheFirstValue() {
        assertEquals(0, searcher.binarySearch(sorted, 1));
    }

    @Test
    void findsTheLastValue() {
        assertEquals(5, searcher.binarySearch(sorted, 11));
    }

    @Test
    void returnsMinusOneForAMissingValue() {
        assertEquals(-1, searcher.binarySearch(sorted, 4));
    }

    @Test
    void linearSearchFindsAValue() {
        assertEquals(2, searcher.linearSearch(sorted, 5));
    }

    @Test
    void linearAndBinarySearchAgreeOnEveryValueInALargerArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i * 2;

        for (int target : new int[]{0, 4, 500, 1998, 1999}) {
            assertEquals(searcher.linearSearch(arr, target), searcher.binarySearch(arr, target));
        }
    }
}
