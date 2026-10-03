# Day 1 - Exercise 2: Scale readings

The weighing station's digital scale sends its readings as text, like `" 23.5 "`. The log book wants numbers. Write four small conversion methods in `src/ScaleReadings.java`:

| Method | What it does | Example |
|---|---|---|
| `parseReading(String reading)` | Text from the scale → `double`. Ignore spaces around it. | `" 23.5 "` → `23.5` |
| `wholeKilos(double kg)` | Drop the decimals with a cast. | `23.9` → `23` |
| `kgFromSacks(int sacks, int kgPerSack)` | Multiply two `int`s, return a `double`. | `3, 50` → `150.0` |
| `asText(int kg)` | `int` → `String`. | `42` → `"42"` |

Notice that `wholeKilos(23.9)` gives `23`, not `24`. A cast to `int` cuts off the decimals; it doesn't round.

## Files

- `src/ScaleReadings.java`: your code goes here.
- `src/ScaleReadingsTest.java`: the tests.
- `solution/ScaleReadings.java`: one answer.

## 📌 Stuck?

- `Double.parseDouble(text)` turns text into a `double`, and `text.trim()` removes spaces from both ends.
- `(int) 23.9` is a cast: it tells Java you accept losing the decimals.
- An `int` fits into a `double` without a cast, because no information is lost.
- `String.valueOf(42)` gives `"42"`.
