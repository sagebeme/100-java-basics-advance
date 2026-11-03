# Day 24 - Exercise 2: Receipts that line up

Now the duka prints receipts. Each one is its own file, and every sale also goes on one line of the day book. Write `src/Receipts.java`.

`write(folder, 7, "Otieno", items)` writes `folder/receipt-0007.txt` and returns its `Path`:

```
MAMA NJERI'S DUKA
Receipt 0007 for Otieno
Sugar 2kg        240
Bread             60
--------------------
TOTAL            300
```

| Method | What it does |
|---|---|
| `line(name, shillings)` | One row, exactly 20 characters: the name on the left (cut to 15 characters if it's longer), the amount on the right. `line("Bread", 60)` is `"Bread             60"`. |
| `write(folder, number, customer, items)` | Writes the receipt above. `items` is a `Map` of name to price, in the order they were bought. Makes `folder` if it doesn't exist yet. **Never overwrites:** if that receipt number already has a file, it throws `FileAlreadyExistsException`, and the old receipt stays as it was. |
| `addToDayBook(dayBook, number, total)` | Adds one line, `0007 300`, to the end of the day book, and makes the file if this is the day's first receipt. |

## Files

- `src/Receipts.java`: your code goes here.
- `src/ReceiptsTest.java`: the tests, in a temporary folder.
- `solution/Receipts.java`: one answer.

```bash
javac -cp "lib/*" -d day24/exercise2/src day24/exercise2/src/*.java
java -cp "day24/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- `String.format("%-15s%5d", name, shillings)`: `%-15s` pads the text to 15 on the right (the `-` means "line up on the left"), and `%5d` pads the number to 5 on the left. `%04d` gives `0007`.
- `Files.createDirectories(folder)` makes every missing folder on the way and does nothing if they're already there.
- How a file is opened is up to you, with `StandardOpenOption`s. `Files.write(file, lines, CREATE_NEW, WRITE)` refuses to touch an existing file. `Files.writeString(file, text, CREATE, APPEND)` adds to the end, making the file first if needed.
- End the day-book line with `%n` in the format, so each entry is on its own line.
