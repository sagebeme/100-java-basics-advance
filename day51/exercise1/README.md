# Day 51 - Exercise 1: Middle values

One slow test can mean the router was busy. Write two methods in `src/Stats.java`:

| Method | Example |
|---|---|
| `median(List<Double> values)` | `[6.2, 30.0, 7.1]` → `7.1`; `[6.2, 7.1, 30.0, 6.0]` → `6.65`; empty → `IllegalArgumentException` |
| `trimmedMean(List<Double> values)` | the average after removing the single lowest and highest value: `[5, 6, 7, 30]` → `6.5`. With fewer than 3 values, the plain average. |

Neither may change the list it's given.

## 📌 Stuck?

`values.stream().sorted().toList()` gives a sorted copy. One answer is in `solution/Stats.java`.
