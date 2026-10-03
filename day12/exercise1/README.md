# Day 12 - Exercise 1: Scope bugs

`src/CherryTally.java` compiles and runs, but both of its answers are wrong. Each bug is about **where a variable lives**. Fix them without rewriting the methods from scratch.

1. **`countRed(String[] cherries)`** should count the `"red"` cherries. For `{"red", "green", "red", "red"}` it should return `3`, but it returns `1`.
2. **`add(int ripe)`** should add to the tally's running total, kept in the `ripe` field. After `add(5)` and `add(3)`, `getRipe()` should be `8`, but it's `0`.

Before you change anything, explain to yourself (or a rubber duck) exactly why each one is wrong. Then run the tests.

## Files

- `src/CherryTally.java`: the buggy code to fix.
- `src/CherryTallyTest.java`: the tests. They fail until both bugs are fixed.
- `solution/CherryTally.java`: the fixed version.

## 📌 Stuck?

- Bug 1: what happens to `count` at the start of every trip round the loop?
- Bug 2: inside `add`, which `ripe` does the name `ripe` mean, the field or the parameter? How do you reach the other one?
