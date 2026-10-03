# Day 12 - Exercise 2: A scale nobody can fiddle

Scales drift, so a clerk calibrates them every morning with a known weight. But a scale whose calibration anyone could set to `+5 kg` would be a gift to a cheat. Write `src/WeighingScale.java` so the calibration is `private` and can only change in small, honest steps:

| Member | What it does |
|---|---|
| `private double offset` | The correction added to every raw reading. Starts at `0`. |
| `boolean calibrate(double newOffset)` | Sets the offset, but only if it's from `-0.5` to `0.5` kg. Otherwise it changes nothing and returns `false`. |
| `double weigh(double rawKg)` | The raw reading plus the offset, never less than `0`. |
| `double getOffset()` | Reads the offset. There's no other way to set it. |

```java
WeighingScale scale = new WeighingScale();
scale.calibrate(-0.2);   // true
scale.weigh(23.7);       // 23.5
scale.calibrate(5);      // false, and the offset stays at -0.2
```

## Files

- `src/WeighingScale.java`: your code goes here.
- `src/WeighingScaleTest.java`: the tests.
- `solution/WeighingScale.java`: one answer.

## 📌 Stuck?

`calibrate` is the guard: check the range first and return `false` before touching the field. For `weigh`, `Math.max(0, rawKg + offset)` stops an empty scale showing `-0.2`.
