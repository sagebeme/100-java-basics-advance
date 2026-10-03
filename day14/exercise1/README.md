# Day 14 - Exercise 1: What size is that?

Coffee shops sell drinks in small, medium and large. Write two methods in `src/CupSize.java`:

| Method | Example |
|---|---|
| `totalMl(int... parts)` | `totalMl(30, 200, 10)` → `240` |
| `cupSize(int ml)` | up to 240 ml → `"small"`, up to 360 ml → `"medium"`, more → `"large"`. `0` or less → `"empty"`. |

Then, in `main`, print the size of a latte made from 30 ml of espresso, 300 ml of milk and 20 ml of foam.

## Files

- `src/CupSize.java`: your code goes here.
- `src/CupSizeTest.java`: the tests.
- `solution/CupSize.java`: one answer.

## 📌 Stuck?

Day 8 covered varargs: inside the method, `parts` is an `int[]`. For `cupSize`, check `"empty"` first, then go up in size.
