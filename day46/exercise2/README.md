# Day 46 - Exercise 2: Is it really cheaper?

Given today's price and the prices seen over the last few weeks, write two methods in `src/PriceStats.java`:

| Method | Example (history `[18999, 18999, 21999, 21999]`) |
|---|---|
| `percentBelowHigh(int today, List<Integer> history)` | today `18499`: `15.9` (rounded to one decimal place), or `0.0` when today is the highest or there's no history |
| `isLowestYet(int today, List<Integer> history)` | today `18499`: `true`; today `18999`: `false` (it's not *lower* than the lowest) |

`isLowestYet` with an empty history is `true`: the first price you see is the lowest you've seen.

## Files

- `src/PriceStats.java`: your code goes here.
- `src/PriceStatsTest.java`: the tests.
- `solution/PriceStats.java`: one answer.

## 📌 Stuck?

`history.stream().mapToInt(Integer::intValue).max()` gives an `OptionalInt`. `(high - today) * 100.0 / high`, then `Math.round(x * 10) / 10.0` for one decimal place.
