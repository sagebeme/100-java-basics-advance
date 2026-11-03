# Day 23 - Exercise 1: Kuku crossing

Today's capstone is Turtle Crossing. This version is set on Thika Road: a kuku (a chicken) has to get from the bottom of the road to the top, one step at a time, and every crossing makes the next level harder. Start with the kuku itself, in `src/Kuku.java`.

| Part | What it does |
|---|---|
| `new Kuku(startX, startY, step, width, height)` | A kuku at its start, on level 1, on a road from `0` to `width` across and `0` to `height` down. A step of 0 or less throws `IllegalArgumentException`. |
| `moveUp()`, `moveDown()`, `moveLeft()`, `moveRight()` | One step, never off the road: `y` stays between `0` and `height`, `x` between `0` and `width`. Up makes `y` smaller. |
| `reachedTheOtherSide()` | Is it at the top (`y == 0`)? |
| `checkCrossing()` | If it has reached the other side: next level, back to the start, and return `true`. Otherwise return `false` and change nothing. |
| `backToStart()` | Back to where it started, on the same level. The game calls this when a car hits it. |
| `getX()`, `getY()`, `getLevel()` | Where it is and how far it's got. |

## Files

- `src/Kuku.java`: your code goes here.
- `src/KukuTest.java`: the tests.
- `solution/Kuku.java`: one answer.

```bash
javac -cp "lib/*" -d day23/exercise1/src day23/exercise1/src/*.java
java -cp "day23/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Keep the start in two `final` fields as well as the current position, so `backToStart` has something to go back to.
- `Math.max(0, y - step)` stops the kuku at the top edge; `Math.min(height, y + step)` stops it at the bottom.
- `checkCrossing` can call `backToStart()` itself, rather than repeating it.
