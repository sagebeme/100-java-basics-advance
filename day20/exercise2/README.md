# Day 20 - Exercise 2: Squares and pixels

Snake on the old Nokia 3310 moved square by square, but the screen only knows pixels. Every grid game keeps two sets of numbers, the cell (`col`, `row`) and the pixel (`x`, `y`), and converts between them all the time. Write the converter in `src/Grid.java`.

| Method | Example, on `new Grid(20, 15, 24)` (20 columns, 15 rows, 24 px squares) |
|---|---|
| `getPixelWidth()`, `getPixelHeight()` | `480`, `360` |
| `contains(col, row)` | `(19, 14)` → `true`, `(20, 0)` → `false`, `(-1, 3)` → `false` |
| `topLeft(col, row)` | `(3, 2)` → `{72, 48}`: where to draw the square |
| `centre(col, row)` | `(3, 2)` → `{84, 60}`: where to draw the food |
| `cellAt(x, y)` | `(85, 49)` → `{3, 2}`. A pixel off the board → `null`. |
| `wrap(col, row)` | Snake leaves one side and comes back on the other: `(20, 5)` → `{0, 5}`, `(-1, 5)` → `{19, 5}`, `(3, -1)` → `{3, 14}` |

A grid with 0 or fewer columns, rows or pixels per cell throws `IllegalArgumentException`.

## Two traps this exercise is about

- **Negative division.** `-5 / 24` is `0` in Java, because it rounds towards zero. So with plain `/`, pixel `x = -5` lands in column 0, which is on the board. Use `Math.floorDiv(-5, 24)`, which gives `-1`.
- **Negative remainder.** `-1 % 20` is `-1`, not `19`. `Math.floorMod(-1, 20)` is `19`, which is what wrapping needs.

## Files

- `src/Grid.java`: your code goes here.
- `src/GridTest.java`: the tests.
- `solution/Grid.java`: one answer.

```bash
javac -cp "lib/*" -d day20/exercise2/src day20/exercise2/src/*.java
java -cp "day20/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- `topLeft` is `{col * cell, row * cell}`; `centre` adds half a cell to each.
- `cellAt` works out the cell with `floorDiv`, then returns `null` if `contains` says no.
