# Day 10 - Exercise 2: Roast timer

A roaster wants to know when to listen for the first crack. Write two methods in `src/RoastTimer.java`:

**`secondsToFirstCrack(int startTempC, double degreesPerMinute)`**: the beans heat up at a steady rate, and first crack is at 196°C. Return the number of seconds until they get there, rounded **up** to a whole second.

- Already at 196°C or hotter: return `0`.
- A rate of `0` or less means they'll never get there: return `-1`.

```java
secondsToFirstCrack(150, 6)  // 46 degrees at 6 a minute = 7.67 minutes = 460 seconds
secondsToFirstCrack(200, 6)  // 0
secondsToFirstCrack(150, 0)  // -1
```

**`clock(int totalSeconds)`**: show seconds the way a roaster's timer does: `460` becomes `"7:40"`, and `65` becomes `"1:05"`.

## Files

- `src/RoastTimer.java`: your code goes here.
- `src/RoastTimerTest.java`: the tests.
- `solution/RoastTimer.java`: one answer.

## 📌 Stuck?

- Handle the two special cases first, each with its own early `return`.
- Seconds are `degrees / degreesPerMinute * 60`. `Math.ceil` rounds up, then cast to `int`.
- `totalSeconds / 60` is the minutes and `totalSeconds % 60` the seconds left over. `String.format("%d:%02d", ...)` pads the seconds to two digits.
