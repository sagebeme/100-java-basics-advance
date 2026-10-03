# Day 1 - Solution

`src/HarvestLog.java` is one finished version of the Harvest Log, and `src/HarvestLogTest.java` checks it.

Things worth noticing:

- **`logLine` does the work, `main` does the talking.** Keeping the sentence-building in its own method means a test can check it without anyone typing at a keyboard.
- **Every answer goes into a variable with the right type.** The name is a `String`, the weight is a `double` because cherries don't come in whole kilograms, and "all ripe" is a `boolean`.
- **`allRipe ? "yes" : "no"`** turns a `boolean` into friendlier text in one line.
- **`.trim()`** removes stray spaces around a number before it's parsed, so `" 23.5"` still works.

Your version doesn't need to match line for line. If the tests pass and the program reads clearly, it's right.
