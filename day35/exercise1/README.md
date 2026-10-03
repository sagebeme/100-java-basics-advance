# Day 35 - Exercise 1: Reading the environment

Write three methods in `src/EnvReader.java`. Each one takes the environment as a `Map<String, String>`, so tests can pass a made-up environment; your `main` passes `System.getenv()`.

| Method | What it does |
|---|---|
| `text(env, name, fallback)` | The variable's value, or `fallback` if it's missing or blank |
| `number(env, name, fallback)` | The value as an `int`, or `fallback` if it's missing or not a whole number |
| `flag(env, name)` | `true` for `true`, `yes`, `1` or `on` (any capitals), otherwise `false` |

```java
Map<String, String> env = Map.of("PORT", "8080", "DEBUG", "Yes", "NAME", "  ");
text(env, "NAME", "Duka")  // "Duka"
number(env, "PORT", 3000)  // 8080
number(env, "NAME", 3000)  // 3000
flag(env, "DEBUG")         // true
```

## Files

- `src/EnvReader.java`: your code goes here.
- `src/EnvReaderTest.java`: the tests.
- `solution/EnvReader.java`: one answer.

## 📌 Stuck?

`Integer.parseInt("abc")` throws a `NumberFormatException`. Catch it and return the fallback.
