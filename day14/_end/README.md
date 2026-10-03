# Day 14 - Solution

`src/MenuDecoder.java` is one finished version, checked by `src/MenuDecoderTest.java`.

Things worth noticing:

- **`identify` reads like the rules table.** Each `if` is one row, in the same order, and each one returns. When a new drink appears, it's one more row in one place.
- **The descriptions are data, not code.** They live in a map, so `explain` is a lookup and the menu loop can list every drink without naming them.
- **Small private helpers** (`askYes`, `askMl`) keep `main` readable. They're `static` and package-private, because only this class needs them.
- **Everything from Days 1 to 13 is here**: `Scanner`, `if` chains, a `while` menu loop, a `Map`, methods with parameters and return values, and input that can't crash the program.
