# Day 22 - Pong: Two Players, One Keyboard

> Pong was one of the first arcade games ever made, and it's still the best way to learn how a ball bounces in code. Today you build it for two players sharing one keyboard.

## 📖 The story

The cyber café in Githurai has one PC that nobody uses for printing anymore. Kamau, who runs it, wants a game two kids can play at once on that one keyboard. Pong is perfect:

- the left player moves with **W** and **S**, the right player with **↑** and **↓**;
- a ball flies across a dark court, bouncing off the top and bottom walls;
- hit it with your paddle and it goes back the other way;
- miss it and your opponent scores, and the ball restarts from the middle, heading towards the player who just scored.

The game runs at about 60 frames a second: every 16ms the ball moves, the game checks what it hit, and the court is redrawn.

The "what did it hit?" questions are in `PongGameLogic`, where the tests check them: `hitsLeftPaddle`, `hitsRightPaddle`, `hitsTopOrBottomWall`, `leftPlayerScores` and `rightPlayerScores`.

## 🎯 Learning Objectives
- Move a ball with a velocity: a speed in `x` and a speed in `y`
- Bounce by flipping the sign of one speed
- Check whether a moving point overlaps a rectangle
- Handle two players' keys in one key handler, and keep paddles inside the court
- Keep score and reset the ball after a point

## 🧰 Java you'll use

**Velocity.** Each frame, the ball moves by its speed. A bounce just reverses one direction:

```java
ballX += ballSpeedX;
ballY += ballSpeedY;
if (ballY <= 0 || ballY >= HEIGHT) ballSpeedY = -ballSpeedY;   // top or bottom wall
```

**Hitting a paddle** means the ball has reached the paddle's `x`, *and* its `y` is between the paddle's top and bottom:

```java
boolean hitsLeft = ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT;
```

**Two players, one handler.** `setOnKeyPressed` gets every key; a `switch` on `e.getCode()` decides whose paddle moves. Before moving, check the paddle stays inside the court: `if (paddle1Y > 0) paddle1Y -= 20;`.

## 🪜 Steps

1. **[Exercise 1: The paddle](exercise1/README.md)** (`exercise1/`): moving up and down without leaving the court, and knowing whether, and where, the ball hit it.
2. **[Exercise 2: The ball, the paddles and the score](exercise2/README.md)** (`exercise2/`): one frame of Pong: walls, paddles that send the ball back faster, and a point when a paddle misses.
3. **Project: Pong** (`_start/src/PongGame.java`).
   1. `PongGameLogic`: the five yes-or-no questions, with their tests.
   2. The key handler for W, S, ↑ and ↓.
   3. `update()`: move the ball, bounce off paddles and walls, and score when it leaves the court.
   4. `draw(gc)`: the court, the paddles, the ball and both scores.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day22/_end/src day22/_end/src/PongGameLogic.java day22/_end/src/PongGameLogicTest.java
java -cp "day22/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To play, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day22/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls PongGame
```

Use `_start/src` for your own version, and `day22/exercise1/src` or `day22/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **The ball goes straight through a paddle**: check the `y` range as well as the `x`. And remember the paddle is drawn from its top edge, so it covers `paddleY` to `paddleY + PADDLE_HEIGHT`.
- **The ball gets stuck shaking inside a paddle**: it's still inside on the next frame, so it flips back. Only bounce if it's moving *towards* the paddle (`ballSpeedX < 0` for the left one). Exercise 2 does this.
- **Only one player can move at a time**: that's how key repeat works with `setOnKeyPressed`. For smooth two-player movement, remember which keys are held down in a `Set<KeyCode>` and move the paddles in `update()`.
- **A paddle slides off the screen**: check the limits before moving it.

## ✅ Checklist
- [ ] Both paddles move, and stay inside the court
- [ ] The ball bounces off the walls and the paddles
- [ ] A miss scores a point for the other player and resets the ball
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 23 is the GUI capstone: get a turtle across a busy road while the traffic speeds up level by level.
