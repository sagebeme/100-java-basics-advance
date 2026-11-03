# Day 21 - Exercise 2: What did the snake hit?

Before the snake moves, the game asks one question about the square it's about to enter: is it a wall, the snake itself, food, or nothing? Write that check in `src/Collision.java`. Squares are `"col,row"` strings, as in Exercise 1.

`Collision.check(next, body, food, cols, rows, growing)` returns one of four words:

| Answer | When |
|---|---|
| `"WALL"` | `next` is off a `cols`×`rows` board (columns `0` to `cols - 1`, rows `0` to `rows - 1`). |
| `"SELF"` | `next` is one of the snake's squares. |
| `"FOOD"` | `next` is the food. |
| `"CLEAR"` | None of those. |

If more than one is true, the first in that list wins: running into the wall ends the game even if the food is lying there.

**The tail trap.** On a normal move, the tail leaves its square at the same moment the head arrives. So a head moving into the square the tail is on right now is *not* a collision. When `growing` is `true`, the tail stays put, so then it is.

```
body: 2,1  2,2  1,2  1,1     the head at 2,1 is about to move left, into 1,1
growing false → CLEAR        the tail moves off 1,1 as the head moves on
growing true  → SELF         the tail stays on 1,1
```

## Files

- `src/Collision.java`: your code goes here.
- `src/CollisionTest.java`: the tests.
- `solution/Collision.java`: one answer.

```bash
javac -cp "lib/*" -d day21/exercise2/src day21/exercise2/src/*.java
java -cp "day21/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- For the wall, turn `next` into two numbers with `split(",")` and `Integer.parseInt`.
- For the tail trap, check against `body.subList(0, body.size() - 1)` when not growing, and the whole `body` when growing. `contains` does the rest.
