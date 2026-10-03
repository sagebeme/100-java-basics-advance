# Day 13 - Exercise 2: Lorry checks

Before a lorry leaves the mill, two checks run. `src/LorryCheck.java` has a bug in each, and neither one crashes. The tests describe the rules, so run them first and let them tell you what's wrong.

| Method | The rule |
|---|---|
| `toTonnes(int kg)` | Kilograms to tonnes, keeping the decimals: `3920` → `3.92` |
| `isOverloaded(double grossKg, double limitKg)` | `true` only when the lorry is **over** the limit. Exactly at the limit is fine. |

For each failing test, write down what you expected and what you got before you change any code.

## Files

- `src/LorryCheck.java`: the buggy code.
- `src/LorryCheckTest.java`: the tests.
- `solution/LorryCheck.java`: the fixed version.

## 📌 Stuck?

- A test failure says `expected: <3.92> but was: <3.0>`. Which kind of division throws away decimals?
- "Over the limit" and "at or over the limit" are different operators.
