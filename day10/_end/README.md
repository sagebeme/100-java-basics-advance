# Day 10 - Solution

`src/RoastChecker.java` is one finished version, checked by `src/RoastCheckerTest.java`.

Things worth noticing:

- **Early returns read like the table.** Each line of `roastLevel` is one row of the README's table, from top to bottom.
- **`-1` and `UNKNOWN` travel together.** `weightLossPercent` returns `-1` for impossible weights, and `levelFromLoss` turns any negative loss into `UNKNOWN`, so bad input can't masquerade as a real roast level.
- **`readingsAgree` is one line**, because it only combines the answers of the other three methods.
