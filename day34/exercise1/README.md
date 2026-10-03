# Day 34 - Exercise 1: A fallback that always answers

Programs that depend on the internet should still do *something* when it's down. Write `firstThatWorks(List<Supplier<String>> sources, String lastResort)` in `src/Fallback.java`. It tries each source in order and returns the first answer that:

- doesn't throw an exception, and
- isn't `null` or blank.

If every source fails, it returns `lastResort`.

```java
firstThatWorks(List.of(
        () -> { throw new RuntimeException("no Wi-Fi"); },
        () -> "  ",
        () -> "from the cache"),
    "built-in questions")          // "from the cache"
```

## Files

- `src/Fallback.java`: your code goes here.
- `src/FallbackTest.java`: the tests.
- `solution/Fallback.java`: one answer.

## 📌 Stuck?

A `Supplier<String>` is anything with a `get()` method that returns a `String`. Call `source.get()` inside a `try`, `catch (RuntimeException e)`, and move on to the next one.
