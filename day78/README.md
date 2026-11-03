# Day 78 - Greedy Choices and Dynamic Programming

> Sometimes grabbing the best-looking option at each step gives the best answer overall. Sometimes it doesn't. Today you learn to tell the difference, and what to do when greed fails: remember the answers to smaller problems.

## 📖 The story

Pastor Wekesa's church hall in Bungoma gets more booking requests than it can take. Every request has a start and end hour, and two bookings can't overlap. He wants as many events as possible in a day. One simple rule does it: **always take the booking that ends earliest**, then the next one that starts after it ends. It's *greedy*, because it makes the best-looking choice right now and never reconsiders, and for this problem it's provably the best.

Greed also works for change. With Kenya's 20, 10, 5 and 1 shilling coins, giving the biggest coin first always uses the fewest coins:

```
Change for KES 38: [20, 10, 5, 1, 1, 1]
```

But change the coins to 4, 3 and 1, and greed gives 6 as `4 + 1 + 1` (three coins) when `3 + 3` (two coins) was possible. When greed fails, **dynamic programming** solves every smaller version of the problem once, writes the answers in a table, and builds the big answer from them:

```
fibonacci(30) = 832040
LCS of "ABCBDAB" and "BDCABA" = 4
Knapsack: 7
```

## 🎯 Learning Objectives
- Recognise a greedy algorithm, and prove one wrong with a counter-example
- Schedule activities by earliest finish time
- Turn a slow recursive definition into a fast table (Fibonacci)
- Fill a 2D table for the longest common subsequence of two strings
- Solve the 0/1 knapsack problem: the best value that fits a weight limit
- Use `long` when the numbers outgrow `int`

## 🧰 What you'll use

**Greedy scheduling:**

```java
sorted.sort((a, b) -> Integer.compare(a.end(), b.end()));   // earliest finish first
for (Activity activity : sorted) {
    if (activity.start() >= lastEnd) {                       // doesn't overlap the last one
        selected.add(activity);
        lastEnd = activity.end();
    }
}
```

**Why plain recursion is slow.** `fib(n) = fib(n-1) + fib(n-2)` written directly calls `fib(28)` twice, `fib(27)` three times, and so on: over a million calls for `fib(30)`. A table works out each one once:

```java
long[] dp = new long[n + 1];
dp[1] = 1;
for (int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
```

**The knapsack table.** `dp[i][w]` = the best value using the first `i` items with a weight limit of `w`. For each item there are two choices, leave it or take it if it fits:

```java
dp[i][w] = dp[i - 1][w];                                              // leave it
if (weights[i - 1] <= w)
    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);  // take it
```

A mkokoteni pusher who can carry 5 units, offered loads weighing 2, 3, 4 and 5 worth 3, 4, 5 and 6, earns the most (7) by taking the first two.

**The longest common subsequence** is the longest set of letters that appears in both strings in the same order, but not necessarily side by side. It's how `git diff` decides which lines stayed the same.

## 🪜 Steps

**Project: Bookings, change and cargo** (the `day78/` folder is a finished version: build your own first, then compare).

1. `GreedyAlgorithms.selectActivities` and `greedyCoinChange`.
2. A test showing greedy change is right for Kenyan coins and wrong for `{4, 3, 1}`.
3. `DynamicProgramming.fibonacci` with a table, and a test for `fibonacci(50)` (12,586,269,025), which doesn't fit in an `int`.
4. `longestCommonSubsequence` and `knapsack`, tested on the textbook examples above.
5. Write `minCoins(coins, amount)` with dynamic programming, and check it gives 2 for `{4, 3, 1}` and 6.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day78
mvn test
mvn -q compile exec:java          # the dynamic programming demo
```

To run the greedy demo too: `java -cp target/classes com.learning.GreedyAlgorithms`.

## 🆘 Stuck?

- **The schedule isn't the biggest possible**: sort by *end* time, not start time or length.
- **Fibonacci goes negative**: it overflowed `int` (at `fib(47)`). Use `long`, and know that even `long` overflows after `fib(92)`.
- **The knapsack takes an item twice**: the "take it" case must read from row `i - 1`, before this item was considered.
- **`ArrayIndexOutOfBoundsException` in a table**: tables are one bigger than the input (`n + 1`), with row 0 meaning "no items yet".

## ✅ Checklist
- [ ] Greedy scheduling by earliest finish
- [ ] Greedy change, with a counter-example where it fails
- [ ] Fibonacci, LCS and knapsack with tables
- [ ] A dynamic programming `minCoins`
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 79 is about testing itself: unit tests with mocks, integration tests with real parts, and measuring coverage.
