# Day 18 - Exercise 2: Kitenge patterns

Achieng designs kitenge prints and wants to try patterns on screen before paying for fabric. Every pattern is a grid of squares, and each square's colour comes from a rule. Write the rules in `src/Pattern.java`, with no JavaFX, so they can be tested. `src/KitengeDemo.java` (already written) draws them.

| Method | Example |
|---|---|
| `isDark(row, col)` | A checkerboard. The top-left square `(0, 0)` is dark, and the colours alternate along every row and column. |
| `stripe(row, palette)` | Rows take the palette's colours in turn, then start again: with 3 colours, row 3 has colour 0. An empty palette throws `IllegalArgumentException`. |
| `spiral(size)` | A `size`×`size` grid numbered 1, 2, 3... clockwise from the top-left, curling inwards. `size` below 1 throws `IllegalArgumentException`. |

`spiral(3)` is:

```
1 2 3
8 9 4
7 6 5
```

`spiral(4)` is:

```
 1  2  3  4
12 13 14  5
11 16 15  6
10  9  8  7
```

## Files

- `src/Pattern.java`: your code goes here.
- `src/PatternTest.java`: the tests.
- `src/KitengeDemo.java`: draws the three patterns once `Pattern` works.
- `solution/Pattern.java`: one answer.

`PatternTest` needs no JavaFX:

```bash
javac -cp "lib/*" -d day18/exercise2/src day18/exercise2/src/Pattern.java day18/exercise2/src/PatternTest.java
java -cp "day18/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To see the patterns, run `KitengeDemo` with the JavaFX SDK (see LEARNING_PATH.md).

## 📌 Stuck?

- Checkerboard: a square is dark when `row + col` is even.
- Stripes: `row % palette.length` is always a valid index.
- Spiral: keep four edges, `top`, `bottom`, `left`, `right`. Fill the top row left to right, then `top++`; the right column downwards, then `right--`; the bottom row right to left, then `bottom--`; the left column upwards, then `left++`. Repeat while `top <= bottom && left <= right`. An odd size leaves a single middle square: check the edges again before each side.
