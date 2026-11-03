# Day 24 - Exercise 1: Reading Mama Njeri's shop log

Mama Njeri's duka writes one line for everything that happens in the day: opening, each sale, restocks, closing. Her son wants answers from the file without reading it by eye. Write them in `src/ShopLog.java`. There's a real day in `src/duka.log`:

```
OPEN 07:00 Mama Njeri
SALE 120 sugar
SALE 60 bread

SALE 250 cooking oil
RESTOCK 12 Sugar 2kg
SALE 45 sugar sachet
CLOSE 21:00
```

| Method | For `duka.log` |
|---|---|
| `lineCount(file)` | `7`: blank lines don't count. |
| `wordCount(file)` | `24`: words are separated by any amount of space. |
| `find(file, word)` | Lines that mention the word in any case, each with its line number (counting from 1, blank lines included): `find(log, "SUGAR")` is `["2: SALE 120 sugar", "6: RESTOCK 12 Sugar 2kg", "7: SALE 45 sugar sachet"]`. |
| `totalSales(file)` | `475`: the amounts on the `SALE` lines added up. |

Every method takes a `Path` and declares `throws IOException`. A missing file must be an error (`NoSuchFileException`), not an empty answer: "the log is missing" and "nothing was sold today" are very different news.

## Files

- `src/ShopLog.java`: your code goes here.
- `src/duka.log`: a day's log to try `main` on.
- `src/ShopLogTest.java`: the tests. They write their own log in a temporary folder (`@TempDir`), so they work wherever you run them.
- `solution/ShopLog.java`: one answer.

```bash
javac -cp "lib/*" -d day24/exercise1/src day24/exercise1/src/*.java
java -cp "day24/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
java -cp day24/exercise1/src ShopLog day24/exercise1/src/duka.log
```

## 📌 Stuck?

- `Files.readAllLines(file)` gives a `List<String>`, one line each. It throws `NoSuchFileException` by itself if the file isn't there, so don't catch it.
- `line.isBlank()` is true for an empty line or one with only spaces.
- Words: `line.strip().split("\\s+")`. Without `strip()`, a line starting with a space gives an empty first word.
- For `find`, loop with an index (`for (int i = 0; ...)`) so you know the line number: it's `i + 1`.
