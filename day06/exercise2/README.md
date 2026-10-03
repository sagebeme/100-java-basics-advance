# Day 6 - Exercise 2: Sack labels

Kenyan green coffee travels in jute sacks that hold 60 kg when full. Write three overloads of `sackLabel` in `src/SackLabels.java`:

| Call | Result |
|---|---|
| `sackLabel("AA")` | `"AA | 60.0 kg"` (a full sack) |
| `sackLabel("AB", 32.5)` | `"AB | 32.5 kg"` |
| `sackLabel("PB", 18, "0412")` | `"Lot 0412 | PB | 18.0 kg"` |

Each overload should call another one rather than build the text from scratch.

## Files

- `src/SackLabels.java`: your code goes here.
- `src/SackLabelsTest.java`: the tests.
- `solution/SackLabels.java`: one answer.

## 📌 Stuck?

Write the two-parameter version first, using `String.format("%s | %.1f kg", grade, kg)`. The one-parameter version calls it with `60`. The three-parameter version puts `"Lot " + lot + " | "` in front of it.
