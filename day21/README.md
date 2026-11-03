# Day 21 - Snake, Part 2: Food, Growing and Game Over

> Yesterday the snake could only wander. Today it eats, grows, scores, and dies when it hits a wall or bites its own tail, just like the one on the Nokia.

## 📖 The story

Baraka's snake from Day 20 moves and wraps around the screen. His little sister played it for ten seconds and said "but what's the point?" Today the game gets a point:

- a mango appears on a random square;
- eating it adds one to the score, and the snake grows by one square;
- the walls are now solid: hitting one ends the game;
- so does running into your own body;
- the snake can't turn straight back on itself.

```
Game Over! Score: 12
```

Every rule in that list is a small question with a yes-or-no answer, so they live in `SnakeGameLogic`, where the tests can ask them: `nextHead`, `hitsWall`, `hitsSelf` and `eatsFood`.

## 🎯 Learning Objectives
- Grow a list by simply not removing the tail
- Check collisions against a list of positions
- Place food on a random grid square with `Random.nextInt`
- Keep game state (`score`, `gameOver`) and stop updating when the game ends
- Block a move that would reverse the snake into itself
- Pull rules out of a GUI class into small, testable methods

## 🧰 Java you'll use

**Growing is not shrinking.** Every step adds a new head. Normally you then remove the tail; on a step where the snake eats, you don't, and it's one square longer:

```java
snake.add(0, newHead);
if (eatsFood(newHead[0], newHead[1], food)) {
    score++;
    spawnFood();
} else {
    snake.remove(snake.size() - 1);
}
```

**Food on the grid.** Pick a square, then turn it into pixels, so the food always lines up with the snake:

```java
food = new int[]{ random.nextInt(WIDTH / CELL_SIZE) * CELL_SIZE,
                  random.nextInt(HEIGHT / CELL_SIZE) * CELL_SIZE };
```

**No U-turns.** Only accept "up" or "down" while the snake is moving sideways (`directionY == 0`), and the other way round.

**Sublists.** `snake.subList(1, snake.size())` is the body without the head. It's a *view* of the same list, not a copy, so changing one changes the other. Exercise 1 shows why that matters.

## 🪜 Steps

1. **[Exercise 1: The snake's body is a list](exercise1/README.md)** (`exercise1/`): moving, growing and getting bitten as list operations, with `subList` as a view rather than a copy.
2. **[Exercise 2: What did the snake hit?](exercise2/README.md)** (`exercise2/`): wall, self, food or clear for the next square, including the tail that moves out of the way.
3. **Project: Complete Snake** (`_start/src/CompleteSnakeGame.java`).
   1. `SnakeGameLogic`: `nextHead`, `hitsWall`, `hitsSelf` and `eatsFood`, with their tests.
   2. `spawnFood()`, and draw the food.
   3. In `update()`: check the wall, then the body, then add the head and either eat or drop the tail.
   4. When the game is over, stop updating and draw `Game Over! Score: N`.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day21/_end/src day21/_end/src/SnakeGameLogic.java day21/_end/src/SnakeGameLogicTest.java
java -cp "day21/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To play, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day21/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls CompleteSnakeGame
```

Use `_start/src` for your own version, and `day21/exercise1/src` or `day21/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **The game ends the moment it starts**: you check the new head against the body *after* adding it, so it always finds itself. Check first, then add.
- **The snake never grows**: on the step it eats, don't remove the tail.
- **Food appears half off the grid**: pick a random square and multiply by `CELL_SIZE`; don't pick a random pixel.
- **Pressing two keys very fast still reverses the snake**: both presses land before the next step, so each looks legal on its own. A fix is to remember the direction the snake last *moved* and check against that.

## ✅ Checklist
- [ ] Food appears on a random square, and eating it grows the snake
- [ ] The score goes up by one for each mango
- [ ] Hitting a wall or the body ends the game
- [ ] The snake can't turn straight back on itself
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 22 builds Pong: two players, two paddles, one ball and a score each.
