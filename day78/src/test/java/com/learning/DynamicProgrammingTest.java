package com.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    private final DynamicProgramming dp = new DynamicProgramming();

    @Test
    void fibonacciOfZeroAndOneAreTheBaseCases() {
        assertEquals(0, dp.fibonacci(0));
        assertEquals(1, dp.fibonacci(1));
    }

    @Test
    void fibonacciMatchesTheKnownSequence() {
        assertEquals(55, dp.fibonacci(10));
        assertEquals(6765, dp.fibonacci(20));
    }

    @Test
    void fibonacciHandlesALargeIndexWithoutOverflowingAnInt() {
        // fib(50) = 12,586,269,025, which does not fit in an int - this is why the DP array is long[].
        assertEquals(12_586_269_025L, dp.fibonacci(50));
    }

    @Test
    void longestCommonSubsequenceOfIdenticalStringsIsTheWholeString() {
        assertEquals(5, dp.longestCommonSubsequence("hello", "hello"));
    }

    @Test
    void longestCommonSubsequenceOfUnrelatedStringsIsZero() {
        assertEquals(0, dp.longestCommonSubsequence("abc", "xyz"));
    }

    @Test
    void longestCommonSubsequenceMatchesTheClassicTextbookExample() {
        assertEquals(4, dp.longestCommonSubsequence("ABCBDAB", "BDCABA"));
    }

    @Test
    void knapsackPicksTheHighestValueCombinationWithinCapacity() {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        assertEquals(7, dp.knapsack(weights, values, 5)); // items 0+1: weight 5, value 7
    }

    @Test
    void knapsackWithZeroCapacityTakesNothing() {
        assertEquals(0, dp.knapsack(new int[]{1, 2}, new int[]{10, 20}, 0));
    }
}
