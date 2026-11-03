# Day 22 - Exercise 2: The ball, the paddles and the score

Now the Eldoret tournament needs the ball: it crosses the court, bounces off the top and bottom walls, comes back off a paddle a little faster each time, and gives away a point when a paddle misses. Write `src/PongBall.java`. `src/Paddle.java` is Exercise 1's answer, already done.

`new PongBall(x, y, dx, dy, width, height)` is a ball on a `width`×`height` court. A `dx` of 0 throws `IllegalArgumentException`: a ball that never goes sideways never reaches anyone.

`step(left, right)` runs one frame and returns `"LEFT"` or `"RIGHT"` when that player scores, otherwise `""`:

1. Move by `dx` and `dy`.
2. Bounce off the top and bottom walls, as in Day 20's ball.
3. Heading left (`dx < 0`) and reached the left paddle's `x` (`x <= left.getX()`)?
   - The paddle `covers` the ball's `y`: bounce back from the paddle's `x` and go **one faster**: `dx` of `-6` becomes `7`.
   - It doesn't: the **right** player scores.
4. The same on the right, the other way round: `dx` of `6` becomes `-7`, and a miss is a point for the **left** player.

After a point, the ball is served from the middle of the court (`width / 2`, `height / 2`) at the speed it started with, towards the player who just lost the point. `dy` stays as it was.

```
court 600 × 400, left paddle at x = 20 covering y 160 to 240
ball at x = 24, y = 200, dx = -6  →  would reach 18: bounce to x = 22, dx = 7
ball at x = 24, y = 100, dx = -9  →  missed: "RIGHT", back to (300, 200), dx = -9
```

## Files

- `src/PongBall.java`: your code goes here.
- `src/Paddle.java`: done, from Exercise 1.
- `src/PongBallTest.java`: the tests.
- `solution/PongBall.java`: one answer.

```bash
javac -cp "lib/*" -d day22/exercise2/src day22/exercise2/src/*.java
java -cp "day22/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Bouncing off a paddle is the same reflection as off a wall: `x = 2 * left.getX() - x`.
- "One faster" while turning round: on the left, `dx = -dx + 1`; on the right, `dx = -dx - 1`.
- Keep the starting speed in a field, `serveSpeed = Math.abs(dx)`, set once in the constructor. Serving is then `dx = -serveSpeed` (towards the left) or `dx = serveSpeed`.
