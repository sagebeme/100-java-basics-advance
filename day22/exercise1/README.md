# Day 22 - Exercise 1: The paddle

A cyber café in Eldoret is running a Pong tournament on its old PCs. Before the ball can bounce, the paddles have to behave: they move up and down, never leave the court, and know whether the ball hit them and where. Write `src/Paddle.java`.

| Part | What it does, on a court 400 high |
|---|---|
| `new Paddle(x, top, height, courtHeight)` | A paddle at `x`, its top edge at `top`. A `top` that would put it partly off the court is pulled back in. A height of 0 or less, or taller than the court, throws `IllegalArgumentException`. |
| `getX()`, `getTop()`, `getBottom()` | `getBottom()` is `top + height`. |
| `moveUp(pixels)`, `moveDown(pixels)` | Moves, stopping at the edge: the top never goes below 0, the bottom never past 400. |
| `covers(y)` | Is `y` between the top and the bottom, edges included? |
| `hitOffset(y)` | Where the ball hit: `-1.0` at the top edge, `0.0` in the middle, `1.0` at the bottom edge. A `y` the paddle doesn't cover throws `IllegalArgumentException`. |

`hitOffset` is how real Pong steers: a ball that hits the paddle's edge flies off at a steeper angle than one that hits the middle. Exercise 2 uses `covers`; the full game uses `hitOffset` too.

## Files

- `src/Paddle.java`: your code goes here.
- `src/PaddleTest.java`: the tests.
- `solution/Paddle.java`: one answer.

```bash
javac -cp "lib/*" -d day22/exercise1/src day22/exercise1/src/*.java
java -cp "day22/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- One private method, `clamp(newTop)`, keeps the paddle in: `Math.max(0, Math.min(courtHeight - height, newTop))`. The constructor, `moveUp` and `moveDown` all use it.
- `hitOffset`: the middle is `top + height / 2.0`, and half the height is `height / 2.0`. Divide the distance from the middle by half the height. The `.0` keeps it a `double`.
