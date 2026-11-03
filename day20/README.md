# Day 20 - Snake, Part 1: A Game Loop on a Grid

> Before smartphones, half of Kenya played Snake on a Nokia 3310 between matatu stops. Over the next two days you build it in JavaFX. Today the snake learns to move.

## 📖 The story

Baraka still has his dad's old Nokia in a drawer, and he swears he once got a snake the length of the whole screen. He wants to build his own version, and he's starting with the part that makes it a *game*: something moving on its own, five times a second, while you steer it with the arrow keys.

By the end of today:

- a 600 × 600 window is a grid of 20-pixel squares, 30 across and 30 down;
- a three-square snake moves one square every 200ms;
- the arrow keys turn it;
- going off one edge brings it back on the opposite side.

There's no food and no game over yet. That's Day 21.

Wrapping around the edges is the one piece of real logic today, so it lives in `SnakeGameLogic.wrap`, which you can test without a window.

## 🎯 Learning Objectives
- Write a **game loop**: update the game, draw it, wait, repeat
- Keep a grid in pixels and in squares, and convert between them
- Store the snake's body as a `List` of positions: add a new head, drop the tail
- Read the keyboard with `setOnKeyPressed` and `KeyCode`
- Keep drawing on the JavaFX thread with `Platform.runLater`

## 🧰 Java you'll use

**A game loop** on its own thread:

```java
new Thread(() -> {
    while (true) {
        update();                                   // move the snake one square
        Platform.runLater(() -> draw(gc));          // draw on the JavaFX thread
        try { Thread.sleep(200); } catch (InterruptedException e) { return; }
    }
}).start();
```

**Moving a list.** The snake is an `ArrayList<int[]>` of `{x, y}` squares, head first. One step is: add the new head at index 0, then remove the last square. The body seems to slide forward, but only two squares changed.

**Direction as two numbers.** `directionX` and `directionY` are each -1, 0 or 1. Right is `(1, 0)`, up is `(0, -1)`, because screen `y` grows downwards. The new head is the old head plus `direction × CELL_SIZE`.

**Keys.** The canvas only hears keys if it can take focus:

```java
canvas.setFocusTraversable(true);
canvas.setOnKeyPressed(e -> {
    switch (e.getCode()) {
        case UP -> { directionX = 0; directionY = -1; }
        case LEFT -> { directionX = -1; directionY = 0; }
        // ...
    }
});
```

## 🪜 Steps

1. **[Exercise 1: The bouncing ball](exercise1/README.md)** (`exercise1/`): one frame of animation at a time: move, then bounce off any edge, so the ball never leaves the screen.
2. **[Exercise 2: Squares and pixels](exercise2/README.md)** (`exercise2/`): converting between grid cells and pixels, and the two traps with negative numbers: `Math.floorDiv` and `Math.floorMod`.
3. **Project: Snake, Part 1** (`_start/src/SnakeGame.java`).
   1. `SnakeGameLogic.wrap(x, y)`: a position off the left edge comes back at the right, and so on for all four edges. Positions on the board are left alone.
   2. `update()`: work out the new head, wrap it, add it, and keep the snake three squares long.
   3. `draw(gc)`: a checkered board, then the snake.
   4. The arrow keys and the game loop.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day20/_end/src day20/_end/src/SnakeGameLogic.java day20/_end/src/SnakeGameLogicTest.java
java -cp "day20/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To play, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day20/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls SnakeGame
```

Use `_start/src` for your own version, and `day20/exercise1/src` or `day20/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **The arrow keys do nothing**: the canvas doesn't have focus. Call `canvas.setFocusTraversable(true)`, and click the window once.
- **The snake leaves a trail and never gets shorter**: remove the last square after adding the new head.
- **It wraps one square too late or too early**: the last square on the right starts at `WIDTH - CELL_SIZE`, not `WIDTH`.
- **The window flickers or throws `Not on FX application thread`**: draw only inside `Platform.runLater`.

## ✅ Checklist
- [ ] The game loop moves the snake on its own
- [ ] The arrow keys steer it
- [ ] It wraps around all four edges
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 21 finishes Snake: food, growing, a score, and game over when it hits a wall or bites itself.
