# Day 25 - Working With CSV: Name All 47 Counties

> Kenya has 47 counties. Most people can name about thirty before they get stuck. Today's game reads the list from a CSV file and keeps score.

## 📖 The story

Mwalimu Chebet teaches Standard 7 social studies in Eldoret. Every year she runs the same challenge: "Name all 47 counties." Every year the class gets stuck somewhere around Tharaka-Nithi and Elgeyo-Marakwet. This year she wants a game the pupils can play on the lab computers:

```
How many of Kenya's 47 counties can you name?
Type 'exit' to stop.

0/47 named. Next county: nairobi
Yes! Nairobi.

1/47 named. Next county: Kampala
Kampala isn't one of the 47 counties.

1/47 named. Next county: exit

You named 1. You missed: Mombasa, Kwale, Kilifi, ...
```

The counties live in `counties.csv`, one per line, numbered in the order the Constitution's First Schedule lists them:

```
code,county
1,Mombasa
2,Kwale
...
47,Nairobi
```

**CSV** ("comma-separated values") is the plainest way to store a table: one row per line, commas between columns, usually a header line first. Every spreadsheet can open and save it, which is why so much real data arrives as CSV.

## 🎯 Learning Objectives
- Read a CSV file: skip the header, split each line into columns
- Pick out one column and keep it in a `List`
- Compare text without caring about capitals or extra spaces
- Use an `enum` for a fixed set of answers (`CORRECT`, `ALREADY_GUESSED`, `INVALID`)
- Know where `split(",")` breaks, and how real CSV handles commas inside a field

## 🧰 Java you'll use

**Reading the table:**

```java
try (Scanner scanner = new Scanner(new File("counties.csv"))) {
    scanner.nextLine();                              // the header: code,county
    while (scanner.hasNextLine()) {
        String[] parts = scanner.nextLine().split(",");
        counties.add(parts[1].trim());               // the second column
    }
}
```

**An enum** is a type with a fixed list of values. A method that returns one can't return a typo:

```java
public enum GuessResult { CORRECT, ALREADY_GUESSED, INVALID }
```

and a `switch` on it is easy to read:

```java
switch (evaluateGuess(guess, counties, guessed)) {
    case CORRECT -> ...
    case ALREADY_GUESSED -> ...
    case INVALID -> ...
}
```

**One spelling.** Players type `nairobi` and `NAIROBI `. `spelling(guess, counties)` finds the county with `equalsIgnoreCase` and returns it as the list spells it, so `Nairobi` is only ever stored one way.

**Where `split` breaks.** A county name has no commas, but plenty of real data does: `"Mama Njeri, Kayole",250`. Exercise 1 builds a reader that copes with quotes.

## 🪜 Steps

1. **[Exercise 1: A CSV reader that survives commas in names](exercise1/README.md)** (`exercise1/`): why `split(",")` breaks on real CSV, and a reader that handles quoted fields, `""` and a choice of delimiter.
2. **[Exercise 2: The co-op's weekly milk report](exercise2/README.md)** (`exercise2/`): totals per farmer, a top list with ties broken by name, averages and shares, with plain loops and a `TreeMap`.
3. **Project: The Counties Game** (`_start/src/CountiesGame.java`).
   1. `spelling(guess, counties)`: the county as the list spells it, or `null`.
   2. `evaluateGuess(guess, counties, guessed)`: `INVALID`, `ALREADY_GUESSED` or `CORRECT`.
   3. `readCounties(file)`: the second column of every line after the header.
   4. `main`: the guessing loop, `exit`, and the list of missed counties at the end.

## 🧪 Run it and test it

```bash
cd day25/_end/src
javac CountiesGame.java
java CountiesGame
```

To test your own version, copy `_end/src/CountiesGameTest.java` into `_start/src`, then from the repo root:

```bash
javac -cp "lib/*" -d day25/_start/src day25/_start/src/*.java
java -cp "day25/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

For an exercise, use `day25/exercise1/src` or `day25/exercise2/src` in both commands. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`counties.csv not found`**: run `java` from the folder that holds the CSV.
- **"code" or "county" turns up as a county**: you didn't skip the header line.
- **The numbers come out instead of the names**: the name is the second column, `parts[1]`. Arrays start at 0.
- **`nairobi` is wrong but `Nairobi` is right**: compare with `equalsIgnoreCase`, after `trim()`.
- **`Murang'a` never matches**: players type a different apostrophe on some phones (`’`). You could treat both as the same.

## ✅ Checklist
- [ ] Read the CSV, skipping the header
- [ ] Guesses ignore capitals and spaces
- [ ] A county can only be counted once
- [ ] The game ends with the score and the missed counties
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 26 meets streams and lambdas, through a call-centre agent spelling names over a crackly line.
