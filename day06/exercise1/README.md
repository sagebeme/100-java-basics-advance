# Day 6 - Exercise 1: Sieve maths

A screen size is the width of a sieve's holes in 64ths of an inch, and one inch is 25.4 mm. Write two methods in `src/SieveMaths.java`:

| Method | Example |
|---|---|
| `screenToMm(int screen)` | `17` → about `6.75` |
| `screenFromMm(double mm)` | `6.8` → `17` (the biggest screen whose holes are no wider than `mm`) |

Check your work with `screenFromMm(screenToMm(15))`, which should give `15` back.

## Files

- `src/SieveMaths.java`: your code goes here.
- `src/SieveMathsTest.java`: the tests.
- `solution/SieveMaths.java`: one answer.

## 📌 Stuck?

- `screen * 25.4 / 64` converts to millimetres.
- Going back, `mm * 64 / 25.4` gives a `double` like `17.13`. Cast it to `int` to drop the decimals.
- Decimals aren't always exact in a computer, so `screenToMm(15)` turned back could be `14.9999...`, and a cast would give `14`. Add a tiny amount before casting, such as `+ 1e-9`.
