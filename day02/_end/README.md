# Day 2 - Solution

`src/CherryToCup.java` is one finished version, checked by `src/CherryToCupTest.java`.

Things worth noticing:

- **Named constants instead of magic numbers.** `cherryKg / CHERRY_PER_KG_GREEN` explains itself; `cherryKg / 6.0` makes the reader guess what 6 is.
- **Each step is its own method.** `summary` just calls `greenKg`, `roastedKg` and `cups`, so each one can be tested on its own.
- **One deliberate cast.** `cups` casts to `int` because you can't brew 0.9 of a cup. Everywhere else the maths stays in `double`.
- **Integer division used on purpose.** In `main`, `totalCups / 365` and `totalCups % 365` split cups into years and days. Dropping the decimals is exactly what you want there.
