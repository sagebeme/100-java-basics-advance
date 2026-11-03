# Day 86 - Portfolio Project: Breakout Game Clone

> A ball, a paddle, a wall of bricks. Breakout is the classic first arcade game to build, because it has everything: movement over time, collisions, scoring, lives and levels.

## 📖 The story

Otieno runs a cyber café in Kisumu, and in the afternoons the kids from the primary school next door crowd round the one machine with games on it. He wants something of his own on the other computers: a brick-breaking game with a score, three lives, and levels that get harder. Nothing to install but Java.

Move the paddle with the arrow keys, keep the ball in play, and knock out every brick. Each brick is worth 10 points. Clear the wall and the next level has an extra row (up to eight) and a slightly faster ball. Lose the ball three times and it's game over.

## 🎯 Learning Objectives
- Move things by velocity × time, so speed doesn't depend on the computer's frame rate
- Detect collisions between a circle and rectangles, and bounce correctly
- Keep the whole game in a model class that can be tested without a window
- Split a long frame into small steps, so a fast ball can't pass through things
- Drive the game with JavaFX's `AnimationTimer`, drawing on a `Canvas`
- Handle held-down keys with pressed and released events

## 🧰 What you'll use

**Movement over time.** The ball has a velocity in pixels per second, and each update moves it by velocity × the time since the last frame:

```java
x += vx * dtSeconds;
y += vy * dtSeconds;
```

On a 60 Hz screen `dt` is about 0.017 s; on a slower one it's bigger, and the ball still moves at the same speed.

**Bouncing.** Hitting a side wall flips `vx`; hitting the top, a brick, or the paddle flips `vy`. The paddle only bounces a ball that's moving *down*, or a ball grazing it on the way up would get stuck.

**Small steps.** If a frame takes half a second (the window was dragged, the computer was busy), a ball at 400 px/s would move 200 px in one jump, past the paddle without ever touching it. So `update(dt)` splits long times into steps of 1/120 s and checks collisions after each one.

**The game loop:**

```java
new AnimationTimer() {
    public void handle(long now) {          // called every frame, with the time in nanoseconds
        double dt = (now - lastNanos) / 1_000_000_000.0;
        game.update(dt);
        render(gc);
    }
}.start();
```

**Held keys.** `setOnKeyPressed` sets `movingLeft = true`, and `setOnKeyReleased` sets it back to `false`. The loop moves the paddle while the flag is set, so movement is smooth instead of jumping with the keyboard's repeat rate.

## 🪜 Steps

**Your brief** (the `day86/` folder is one finished version: build your own first, then compare).

1. `Ball`, `Paddle` and `Brick`, each knowing its edges.
2. `BreakoutGame`: walls, paddle and bricks, score, lives, levels, and game over, with small steps in `update`.
3. Tests: movement, each wall, the paddle (and not when moving away), a brick, losing all lives, nothing after game over, the next level, the paddle kept on the board, a long pause, and the wall's size limit.
4. `BreakoutApp`: the canvas, the keys and the loop.
5. Make it yours: change the bounce angle by where the ball hits the paddle (the edges send it sideways), add a pause key and a restart after game over, or keep a high score.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day86
mvn test           # the whole game, no window needed
mvn javafx:run     # play: left and right arrow keys
```

## 🆘 Stuck?

- **The ball is faster on a fast computer**: you moved it a fixed amount per frame. Multiply by `dt`.
- **The ball gets stuck inside the paddle**: only bounce when moving down, and put the ball back on top of the paddle when it bounces.
- **The ball goes through the paddle now and then**: a frame was slow and the ball jumped past it. Split `dt` into small steps.
- **Two bricks break at once and the ball keeps going**: two flips cancel out. Handle one brick per step.
- **The keys do nothing**: the scene needs focus; click the window, or call `canvas.requestFocus()`.

## ✅ Checklist
- [ ] Movement based on time, not frames
- [ ] Walls, paddle and bricks bounce correctly
- [ ] Score, lives, levels and game over
- [ ] The game model tested without a window, including a slow frame
- [ ] Added paddle angles, pause, restart or a high score
- [ ] Committed code to Git

## ➡️ Next

Day 87 is a web app: a café finder for remote workers, with search, filters and an API.
