# Day 23 - Exercise 2: Traffic on Thika Road

The kuku needs something to dodge. Cars appear at the left edge in random lanes, drive right, and disappear off the other side; the higher the level, the faster they go. `src/Car.java` is done. Write the road that manages them all, `src/Traffic.java`.

| Part | What it does |
|---|---|
| `new Traffic(width, laneHeight)` | An empty road `width` wide. Lane 0 covers `y` from `0` to `laneHeight - 1`, lane 1 the next `laneHeight`, and so on. |
| `add(car)`, `cars()` | Put a car on the road; get a copy of the list of cars. |
| `tick()` | Every car moves once. Then any car whose `x` is past `width` is removed. |
| `hits(x, y)` | Is a car on that point? It must be in the lane that `y` falls in, with `x` between the car's `x` and `x + Car.LENGTH`, both ends included. |
| `spawn(random, lanes, chancePercent, level)` | For each lane from 0 up, roll `random.nextInt(100)`. Below `chancePercent`, a new car starts in that lane at `x = -Car.LENGTH`, just off the screen, at speed `2 + level`. |

## Why `spawn` takes a `Random`

If `Traffic` made its own `new Random()`, every test run would get different cars, and you couldn't say what the right answer is. Passing it in lets the game use a real `Random` and the test use a loaded one. Look at `LoadedDice` in the test: it's a subclass of `Random` (Day 19) that overrides `nextInt` to roll whatever numbers the test chose.

## Files

- `src/Traffic.java`: your code goes here.
- `src/Car.java`: done.
- `src/TrafficTest.java`: the tests.
- `solution/Traffic.java`: one answer.

```bash
javac -cp "lib/*" -d day23/exercise2/src day23/exercise2/src/*.java
java -cp "day23/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Removing from a list while a `for` loop walks it throws `ConcurrentModificationException`. Move every car first, then `cars.removeIf(car -> car.getX() > width);`.
- The lane under a point is `y / laneHeight`.
- Roll exactly once per lane, in lane order. The test's dice hand out their numbers in that order.
