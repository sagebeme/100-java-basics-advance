# Day 7 - Solution

`src/WeighingStation.java` is one finished version, checked by `src/WeighingStationTest.java`.

Things worth noticing:

- **Every rule is in a method, and `main` only talks.** The tests check all the rules without anyone typing at a menu.
- **`record` checks before it changes anything.** It either adds to both lists or to neither, so the lists can never get out of step.
- **`totalFor` walks both lists with one index.** `farmers.get(i)` and `kgs.get(i)` are always the same delivery.
- **A bad weight doesn't crash the menu.** `main` checks the text looks like a number before parsing it, and `record` refuses zero or negative weights.

Two parallel lists work, but they're fragile: one wrong `add` and every name points at the wrong weight. On Day 9 you'll meet `Map`, which keeps each name and its total together.
