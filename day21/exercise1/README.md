# Day 21 - Exercise 1: The snake's body is a list

A snake is a list of squares, head first. Moving is two list operations: add a new head at the front, remove the tail at the back. Growing is "add, but don't remove". Getting bitten is "throw away everything after this square". Write it in `src/Snake.java`, with each square as a string like `"5,3"` (column 5, row 3).

| Part | What it does |
|---|---|
| `new Snake(col, row, length)` | A snake lying flat with its head at `(col, row)` and its body stretching left: `new Snake(5, 3, 3)` is `["5,3", "4,3", "3,3"]`. A length below 1 throws `IllegalArgumentException`. |
| `body()` | Every square, head first. A copy: changing it doesn't change the snake. |
| `head()` | The first square. |
| `move(dx, dy)` | One square in that direction: `move(1, 0)` is right, `move(0, -1)` is up. The tail drops off, unless the snake is growing. |
| `grow()` | The next `move` keeps the tail, so the snake is one longer. |
| `tail(n)` | The last `n` squares, in order. Asking for more than the snake has gives the whole snake. |
| `biteAt(index)` | Cuts the snake off after square `index`: `biteAt(1)` keeps squares 0 and 1. |

## Files

- `src/Snake.java`: your code goes here.
- `src/SnakeTest.java`: the tests.
- `solution/Snake.java`: one answer.

```bash
javac -cp "lib/*" -d day21/exercise1/src day21/exercise1/src/*.java
java -cp "day21/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Keep the squares in a `private final List<String> squares = new ArrayList<>();`. `squares.add(0, newHead)` puts the head at the front; `squares.remove(squares.size() - 1)` takes the tail off.
- Turn `"5,3"` back into numbers with `split(",")` and `Integer.parseInt`.
- `subList(from, to)` is a *view* of part of the list, not a copy. That makes `biteAt` one line, `squares.subList(index + 1, squares.size()).clear();`, and it's why `tail` and `body` must wrap theirs in `new ArrayList<>(...)` before handing them out.
