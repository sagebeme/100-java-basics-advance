# Day 11 - Exercise 2: Weather dice

Weather is random, but a program that uses randomness still needs testing. Write two methods in `src/WeatherDice.java`, both taking the `Random` as a parameter:

| Method | What it does |
|---|---|
| `between(Random rng, double min, double max)` | A random decimal from `min` up to, but not including, `max` |
| `chance(Random rng, double percent)` | `true` about `percent` times in every 100 calls. `chance(rng, 0)` is never `true`, and `chance(rng, 100)` always is. |

Then, in `main`, roll `chance(rng, 5)` for each of the 90 days of the main crop, and count the days with a hailstorm.

The tests use `new Random(7)`, a **seeded** `Random`, so the "random" numbers are the same every run. They check that your results stay in range, and that `chance(rng, 25)` comes out `true` roughly a quarter of the time over many tries.

## Files

- `src/WeatherDice.java`: your code goes here.
- `src/WeatherDiceTest.java`: the tests.
- `solution/WeatherDice.java`: one answer.

## 📌 Stuck?

- `rng.nextDouble()` is from 0.0 up to 1.0. Stretch it: `min + rng.nextDouble() * (max - min)`.
- `rng.nextDouble() * 100` is from 0 up to 100. It's below `percent` exactly `percent` times in 100, on average.
