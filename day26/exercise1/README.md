# Day 26 - Exercise 1: Sorting the Sacco's fleet with lambdas

The Sacco manager keeps asking for the fleet list in a different order: by plate, by size, by passenger rating. And "just the route 46 ones", "only the big ones". Instead of a new method for every request, write small **functions** that can be handed to `sort` and to a picker. `src/Matatu.java` is done; write `src/Fleet.java`.

| Method | Returns | What it means |
|---|---|---|
| `byPlate()` | `Comparator<Matatu>` | Plates in alphabetical order. |
| `mostSeatsFirst()` | `Comparator<Matatu>` | Biggest first. |
| `bestRatedThenPlate()` | `Comparator<Matatu>` | Highest rating first; equal ratings by plate. |
| `onRoute(route)` | `Predicate<Matatu>` | Is it on that route? |
| `atLeastSeats(n)` | `Predicate<Matatu>` | Does it have `n` seats or more? |
| `label()` | `Function<Matatu, String>` | `"KDA 123A (route 46, 33 seats)"` |
| `pick(fleet, test, describe)` | `List<String>` | The matatus that pass `test`, in fleet order, each turned into text by `describe`. |

Once those exist, the manager's requests are one line each, and they combine without any new code:

```java
fleet.sort(Fleet.bestRatedThenPlate());
Fleet.pick(fleet, Fleet.onRoute("46").and(Fleet.atLeastSeats(20)), Fleet.label());
Fleet.pick(fleet, Fleet.atLeastSeats(20).negate(), Matatu::getPlate);
```

`Matatu::getPlate` is a **method reference**: a short way to write the lambda `m -> m.getPlate()`.

## Files

- `src/Fleet.java`: your code goes here.
- `src/Matatu.java`: done.
- `src/FleetTest.java`: the tests.
- `solution/Fleet.java`: one answer.

```bash
javac -cp "lib/*" -d day26/exercise1/src day26/exercise1/src/*.java
java -cp "day26/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- A comparator is a lambda that takes two and returns negative, zero or positive: `(a, b) -> a.getPlate().compareTo(b.getPlate())`.
- `Comparator` has helpers that save the arithmetic: `Comparator.comparingInt(Matatu::getSeats).reversed()`, and `.thenComparing(byPlate())` to break ties.
- A predicate is a lambda that answers yes or no: `m -> m.getRoute().equals(route)`. Note it uses the method's parameter, `route`: a lambda can read variables around it.
- Inside `pick`, a `Predicate` is used with `test.test(m)` and a `Function` with `describe.apply(m)`.
