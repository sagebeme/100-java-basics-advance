# Day 20 - Exercise 1: The bouncing ball

A school in Machakos wants a screensaver for the staff-room computer: a football bouncing around the screen, never leaving it. Every animation is the same loop: move a little, check the edges, draw, repeat. Write the "move and check" part in `src/Ball.java`, with no JavaFX, so it can be tested frame by frame.

| Part | What it does |
|---|---|
| `new Ball(x, y, dx, dy, width, height)` | A ball at `(x, y)` moving `dx` across and `dy` down per frame, on a `width`×`height` screen. `x` runs from `0` to `width`, `y` from `0` to `height`. |
| `step()` | Moves one frame. A ball that would pass an edge bounces: it reverses that direction and ends up as far inside the edge as it would have gone past it. |
| `step(frames)` | Runs `step()` that many times. |
| `getX()`, `getY()`, `getDx()`, `getDy()` | Where it is and where it's heading. |

A bounce off the right edge, on a screen 100 wide:

```
x = 96, dx = 10  →  it would reach 106, 6 past the edge
                 →  x = 94, dx = -10
```

The ball is treated as a point to keep the maths simple. A ball that starts off the screen, or a screen of 0 or less, throws `IllegalArgumentException`.

## Files

- `src/Ball.java`: your code goes here.
- `src/BallTest.java`: the tests.
- `solution/Ball.java`: one answer.

```bash
javac -cp "lib/*" -d day20/exercise1/src day20/exercise1/src/*.java
java -cp "day20/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Do the two directions separately: across and down never affect each other, which is also why a corner bounce just works.
- Past the right edge: `x = 2 * width - x`. Past the left edge (below 0): `x = -x`. Flip `dx` in both cases.
- Keep the fields `private`: if anything outside could set `x`, the "never leaves the screen" promise would be gone.
