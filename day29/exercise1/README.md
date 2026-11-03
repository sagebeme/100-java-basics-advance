# Day 29 - Exercise 1: Passwords for the cyber café staff

A cyber café in Eldoret is giving each of its staff a login for the booking computer. The owner wants the passwords made by the computer, printed on a slip, and strong. Write the generator and a strength checker in `src/PasswordGen.java`.

Two things go wrong with the obvious version, "pick random characters from everything":

1. By chance, a password can come out with no digit at all, and the booking system then refuses it. So put **one of each chosen kind** in first, fill up from all of them, and shuffle, or the digit will always sit in the same place.
2. On a printed slip, `l`, `1`, `I`, `O` and `0` look alike. The sets in the file already leave them out.

| Method | What it does |
|---|---|
| `generate(length, upper, digits, symbols, random)` | A password of exactly `length` characters. Lower case is always used; the three flags add the other sets. At least one character from each set in use, in a random order. If `length` is too short to hold one of each, throw an `IllegalArgumentException`. |
| `kinds(password)` | How many of the four kinds it uses: lower case, upper case, digits, anything else. |
| `strength(password)` | `"weak"` if under 8 characters or only one kind; `"strong"` if 12 or more with at least 3 kinds; otherwise `"medium"`. |

Use only the `Random` you're given. The tests pass in `new Random(7)`, which gives the same numbers every run, so they can check that the same seed gives the same password.

## Files

- `src/PasswordGen.java`: your code goes here.
- `src/PasswordGenTest.java`: the tests.
- `solution/PasswordGen.java`: one answer.

```bash
javac -cp "lib/*" -d day29/exercise1/src day29/exercise1/src/*.java
java -cp "day29/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Put the chosen sets in a `List<String>`. Its size is the shortest length allowed, and `String.join("", sets)` is the pool to fill up from.
- Collect characters in a `List<Character>` so that `Collections.shuffle(chars, random)` can mix them, then build the `String` with a `StringBuilder`.
- `Character.isLowerCase`, `isUpperCase` and `isDigit` sort each character into its kind; anything else is a symbol.
- For real systems, use `java.security.SecureRandom`, which is a `Random` too, so `generate` takes it without a change.
