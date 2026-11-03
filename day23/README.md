# Day 23 - Capstone: Turtle Crossing

> The GUI capstone. Get a turtle across seven lanes of traffic. Every time it makes it, there's one more car on the road.

## 📖 The story

Anyone who has crossed Thika Road on foot knows the feeling. Today's game is that feeling, starring a turtle:

- the turtle starts at the bottom of a 600 × 800 window and moves one step at a time with the arrow keys;
- cars in matatu colours drive across seven lanes, each at its own speed, and wrap around when they leave the screen;
- reach the top and you go up a level: the turtle goes back to the start, and the road gets one more car;
- touch a car and it's back to level 1.

There's nothing new in Java today. This is the day you put Days 18 to 22 together on your own: a canvas, a game loop, keys, a list of moving things, collisions and levels.

The rules live in `TurtleCrossingLogic`: `wrapCarX`, `collides` and `hasReachedTop`.

## 🎯 Learning Objectives
- Plan and build a complete game from a short brief
- Keep many moving objects in a list and update them every frame
- Detect collisions between two rectangles
- Add levels that make the game harder
- Reset the game cleanly after a crash

## 🧰 Java you'll use

**Two rectangles overlap** when each one starts before the other ends, on both axes:

```java
boolean collides = playerX < carX + CAR_WIDTH && playerX + PLAYER_SIZE > carX
                && playerY < carY + CAR_HEIGHT && playerY + PLAYER_SIZE > carY;
```

Draw it on paper with two boxes if it doesn't click: it's the most useful four lines in game programming.

**A car as an array.** Each car is a `double[]` of `{x, lane y, speed}`. That works for a small game; on Day 16 you saw why a `Car` class would be clearer, and Exercise 2 uses one.

**Levels.** `spawnCars()` makes `5 + level` cars, sharing the seven lanes (`y = 100` to `700`), each with a random starting `x` and a speed between 1 and 4 pixels a frame.

## 🪜 Steps

1. **[Exercise 1: Kuku crossing](exercise1/README.md)** (`exercise1/`): a player that steps in four directions without leaving the road, and moves up a level each time it crosses.
2. **[Exercise 2: Traffic on Thika Road](exercise2/README.md)** (`exercise2/`): cars that spawn at random, move every tick and leave the road, with the `Random` passed in so tests can load the dice.
3. **Project: Turtle Crossing** (`_start/src/TurtleCrossing.java`).
   1. `TurtleCrossingLogic`: `wrapCarX`, `collides` and `hasReachedTop`, with their tests.
   2. The arrow keys, keeping the turtle on the screen.
   3. `spawnCars()` and `update()`: move and wrap the cars, check for a crash, check for the top.
   4. `draw(gc)`: verges and tarmac, the cars, the turtle, and a `Level N` badge.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day23/_end/src day23/_end/src/TurtleCrossingLogic.java day23/_end/src/TurtleCrossingLogicTest.java
java -cp "day23/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To play, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day23/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls TurtleCrossing
```

Use `_start/src` for your own version, and `day23/exercise1/src` or `day23/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **Crashes happen when the turtle is clearly clear of a car**: the sizes in your collision check don't match what you draw. Use the same constants in both.
- **Higher levels don't feel harder**: check where new cars go. If their `y` keeps growing with the car count, they end up below the window.
- **`ConcurrentModificationException`**: the game loop and `spawnCars()` changed the list at the same time. Do all changes to `cars` inside `update()`.
- **Where do I start?** The logic class and its tests, then a turtle you can move, then one car, then many.

## ✅ Checklist
- [ ] The turtle moves with the arrow keys and stays on screen
- [ ] Cars move at different speeds and wrap around
- [ ] Touching a car resets the game
- [ ] Reaching the top adds a level and a car
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 24 leaves the window behind and works with files: reading a letter template and writing one personalised letter per guest.
