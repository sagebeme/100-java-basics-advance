# Day 8 - Exercise 2: Any number of sacks

Some farmers bring one sack, others bring five. Write two varargs methods in `src/SackTotals.java`:

| Call | Result |
|---|---|
| `totalKg(12.5, 8, 20)` | `40.5` |
| `totalKg()` | `0.0` |
| `averageKg(10, 20, 30)` | `20.0` |
| `averageKg()` | `0.0` (not a crash, and not `NaN`) |

## Files

- `src/SackTotals.java`: your code goes here.
- `src/SackTotalsTest.java`: the tests.
- `solution/SackTotals.java`: one answer.

## 📌 Stuck?

- Inside the method, `double... sacks` is just a `double[]`, so `sacks.length` and an enhanced `for` both work.
- `averageKg` can call `totalKg(sacks)`: you can pass the array straight on.
- Dividing by zero sacks gives `NaN` ("not a number"). Check `sacks.length == 0` first.
