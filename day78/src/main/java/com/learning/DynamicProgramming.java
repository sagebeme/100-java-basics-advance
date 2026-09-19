package com.learning;

public class DynamicProgramming {

    public long fibonacci(int n) {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Classic 2D dynamic programming: the length of the longest sequence of characters that
     * appears in both strings, in order, but not necessarily contiguously.
     */
    public int longestCommonSubsequence(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    /**
     * The 0/1 knapsack problem: the highest total value achievable without exceeding the given
     * capacity, where each item can be taken at most once.
     */
    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w]; // not taking item i-1
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println("fibonacci(30) = " + dp.fibonacci(30));
        System.out.println("LCS of \"ABCBDAB\" and \"BDCABA\" = " + dp.longestCommonSubsequence("ABCBDAB", "BDCABA"));
        System.out.println("Knapsack: " + dp.knapsack(new int[]{2, 3, 4, 5}, new int[]{3, 4, 5, 6}, 5));
    }
}
