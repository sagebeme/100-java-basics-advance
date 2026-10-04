# Day 47 - Exercise 2: How a wait works

`WebDriverWait.until(...)` isn't magic. It checks a condition, sleeps a little, and checks again, until the condition is true or time runs out. Write your own in `src/Waiter.java`:

```java
boolean until(BooleanSupplier condition, Duration timeout, Duration poll)
```

- Check `condition` straight away; if it's `true`, return `true`.
- Otherwise sleep for `poll` and check again, until `timeout` has passed since you started. Then return `false`.
- Use the `Clock` and `Sleeper` given to the constructor, never `System.currentTimeMillis()` or `Thread.sleep` directly. That way the tests can use a fake clock and finish instantly.

## 📌 Stuck?

`Instant deadline = clock.instant().plus(timeout);` then loop while `clock.instant().isBefore(deadline)`. Check the condition one last time at the deadline. One answer is in `solution/Waiter.java`.
