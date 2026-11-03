# Day 28 - Exercise 1: The invigilator's countdown

At a school in Nyeri, the mock exams are timed on a projector at the front of the hall. The invigilator starts the clock, pauses it when the power blinks, and the alarm must ring **once** when time is up, not every second after. Write the clock's logic in `src/CountdownTimer.java`.

The clock doesn't watch the wall time itself. Something outside calls `tick()` once a second (in the Pomodoro app, a JavaFX `Timeline`). That keeps the logic testable: a test can call `tick()` a thousand times in a millisecond.

| Method | What it does |
|---|---|
| `new CountdownTimer(seconds)` | A stopped clock showing `seconds`. Refuse 0 or less with an `IllegalArgumentException`. |
| `start()` | Starts or carries on counting. A clock already at 0 stays stopped. |
| `pause()` | Stops counting and keeps the time left. |
| `reset()` | Back to the starting time, stopped. |
| `tick()` | One second passes, if running. Returns `true` only on the tick that reaches 0, then the clock stops itself. |
| `isRunning()`, `isComplete()`, `getSecondsLeft()` | What the projector shows. |
| `format(seconds)` (static) | `1500` → `"25:00"`, `65` → `"01:05"`. Minutes keep counting past 59: a 75-minute paper is `"75:00"`. |

## Files

- `src/CountdownTimer.java`: your code goes here.
- `src/CountdownTimerTest.java`: the tests.
- `solution/CountdownTimer.java`: one answer.

```bash
javac -cp "lib/*" -d day28/exercise1/src day28/exercise1/src/*.java
java -cp "day28/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

`java -cp day28/exercise1/src CountdownTimer` runs a three-second countdown in the terminal.

## 📌 Stuck?

- Keep two numbers: the seconds it started with (for `reset`) and the seconds left.
- In `tick()`, return straight away if it isn't running. Otherwise take one off; if that made it 0, stop and return `true`.
- `String.format("%02d:%02d", seconds / 60, seconds % 60)` pads each part to two digits.
