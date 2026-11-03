# Day 19 - Exercise 2: The same number plate, typed three ways

The estate gate keeps a list of residents' cars. The guard types plates however he likes: `KDA 123A`, `kda123a`, ` KDA  123A `. They're all the same car, but to Java they're three different strings, and the list fills up with duplicates. Write `src/Plate.java`, which overrides the three methods every class inherits from `Object`:

| Method | What it does |
|---|---|
| `new Plate(text)` | Tidies the text: capitals, no spaces at the ends, one space after the three letters. Kenyan plates are 3 letters, 3 digits, 1 letter (`KDA 123A`). Anything else throws `IllegalArgumentException`. |
| `toString()` | The tidy form: `"KDA 123A"`. |
| `equals(other)` | `true` for another `Plate` with the same tidy form. `false` for `null`, for a `String`, for anything that isn't a `Plate`. |
| `hashCode()` | Equal plates must give equal hash codes. |

Why `hashCode` too? A `HashSet` first sorts objects into buckets by their hash code, and only then calls `equals` inside one bucket. Two equal plates with different hash codes land in different buckets, never get compared, and both stay in the set.

## Files

- `src/Plate.java`: your code goes here.
- `src/PlateTest.java`: the tests.
- `solution/Plate.java`: one answer.

```bash
javac -cp "lib/*" -d day19/exercise2/src day19/exercise2/src/*.java
java -cp "day19/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Tidy first, then check: remove every space (`text.replace(" ", "")`), upper-case it, and check the 7 characters with `matches("[A-Z]{3}\\d{3}[A-Z]")`. Then put the space back: `squashed.substring(0, 3) + " " + squashed.substring(3)`.
- `equals` starts with `if (!(other instanceof Plate)) return false;`, then casts, `Plate that = (Plate) other;`, and compares the tidy strings.
- `hashCode` can return the tidy string's own `hashCode()`: equal strings always have equal hash codes.
