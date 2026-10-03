# Day 3 - Solution

`src/CherrySorter.java` is one finished version, checked by `src/CherrySorterTest.java`.

Things worth noticing:

- **The order of the checks is the rulebook.** Damage is checked before colour, so a red cherry with holes is rejected. Swap the order and a test fails.
- **Each `if` returns straight away.** Once a rule matches there's nothing more to check, so there's no need for `else` after a `return`.
- **The colour is cleaned once**, at the top, so every comparison after it can be simple.
- **`isYes`** is a tiny helper that turns `"yes"`, `"Yes "` and `"YES"` into `true`. `main` uses it twice instead of repeating the same code.
