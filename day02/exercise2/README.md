# Day 2 - Exercise 2: Farm maths

Three small calculations the cooperative does every week. Each one has a trap where `int` and `double` meet.

| Method | Example | The trap |
|---|---|---|
| `averageKgPerTree(int totalKg, int trees)` | `25, 10` → `2.5` | `25 / 10` is `2` |
| `sacksNeeded(int kg, int sackSize)` | `120, 50` → `3` | `120 / 50` is `2`, but 2 sacks leave 20 kg on the ground |
| `percentRipe(int ripe, int total)` | `45, 60` → `75` | `45 / 60 * 100` is `0` |

## Files

- `src/FarmMaths.java`: your code goes here.
- `src/FarmMathsTest.java`: the tests.
- `solution/FarmMaths.java`: one answer.

## 📌 Stuck?

- Cast one side before dividing: `(double) totalKg / trees`.
- `Math.ceil(x)` rounds a `double` up, and you then cast it to `int`.
- For a percentage of two `int`s, multiply first: `ripe * 100 / total`.
