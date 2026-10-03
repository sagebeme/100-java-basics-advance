# Day 10 - Exercise 1: How much caffeine?

Health guidance for most adults is no more than about **400 mg** of caffeine a day. Write two methods in `src/Caffeine.java`:

`caffeineMg(String drink)` returns the typical caffeine in one cup, using a `switch`. Ignore capitals and spaces around the name. Anything not on the list returns `0`.

| Drink | mg |
|---|---|
| `espresso` | 63 |
| `filter` | 95 |
| `instant` | 62 |
| `chai` | 40 |
| `decaf` | 3 |

`overDailyLimit(String... drinks)` returns `true` if the drinks add up to **more than** 400 mg. It takes any number of drinks, like Day 8's varargs.

```java
overDailyLimit("filter", "filter", "chai")               // 230 mg: false
overDailyLimit("filter", "filter", "filter", "filter", "espresso") // 443 mg: true
```

Chai counts too. Black tea has caffeine, just less than coffee.

## Files

- `src/Caffeine.java`: your code goes here.
- `src/CaffeineTest.java`: the tests.
- `solution/Caffeine.java`: one answer.

## 📌 Stuck?

- `switch (drink.trim().toLowerCase()) { case "espresso" -> ...; default -> ...; }`
- A method can `return` a `switch` directly: `return switch (...) { ... };`
- For `overDailyLimit`, add `caffeineMg(drink)` for each drink, then return the comparison itself: `return total > 400;`.
