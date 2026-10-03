# Day 9 - Exercise 2: Who came both days?

The clerk has two lists of who delivered on Monday and on Tuesday. Some farmers came more than once a day, so names repeat. Write two methods in `src/DeliveryDays.java` that answer with sets:

| Method | Answer |
|---|---|
| `bothDays(monday, tuesday)` | Farmers who came on both days |
| `onlyTuesday(monday, tuesday)` | Farmers who came on Tuesday but not on Monday |

```java
monday:  ["Wanjiru", "Otieno", "Wanjiru", "Achieng"]
tuesday: ["Otieno", "Kip", "Kip", "Wanjiru"]
```

`bothDays` gives `{Otieno, Wanjiru}`, and `onlyTuesday` gives `{Kip}`.

## Files

- `src/DeliveryDays.java`: your code goes here.
- `src/DeliveryDaysTest.java`: the tests. They don't care about order.
- `solution/DeliveryDays.java`: one answer.

## 📌 Stuck?

- `new HashSet<>(monday)` makes a set from a list, dropping the repeats.
- `retainAll(other)` keeps only what's also in `other`, and `removeAll(other)` drops everything that's in `other`.
