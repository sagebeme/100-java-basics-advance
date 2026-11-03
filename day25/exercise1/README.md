# Day 25 - Exercise 1: A CSV reader that survives commas in names

A dairy co-op in Nyeri exports its milk deliveries as a CSV file, `src/deliveries.csv`:

```
farmer,date,litres,grade,note
Wanjiku Mwangi,2026-11-02,18,A,
"Kamau, John",2026-11-02,12,B,"late, truck broke down"
Njoroge Kariuki,2026-11-02,25,A,"said ""asante"" twice"
```

`line.split(",")` breaks on row 2: it gives `"Kamau` and ` John"` as two fields, and the note turns into two more. Real CSV has two rules that `split` doesn't know:

1. A field in double quotes can contain commas. The quotes aren't part of the value.
2. Inside quotes, two double quotes `""` stand for one `"`.

Write a reader that follows them, in `src/Csv.java`.

| Method | What it does |
|---|---|
| `parseLine(line, delimiter)` | One line into a list of fields. Empty fields count, including a last one after a trailing delimiter: `a,,c,` is `["a", "", "c", ""]`. A quote that's opened and never closed throws `IllegalArgumentException`. |
| `readRows(file, delimiter, hasHeader)` | Every row of a file, as lists of fields. Skips the first line if `hasHeader`, and skips blank lines. |

The delimiter is a parameter because many spreadsheets in Kenya and Europe save with `;`, keeping `,` for decimals: `Kamau, John;12,5;B`.

## Files

- `src/Csv.java`: your code goes here.
- `src/deliveries.csv`: the co-op's file, to try `main` on.
- `src/CsvTest.java`: the tests.
- `solution/Csv.java`: one answer.

```bash
javac -cp "lib/*" -d day25/exercise1/src day25/exercise1/src/*.java
java -cp "day25/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
java -cp day25/exercise1/src Csv day25/exercise1/src/deliveries.csv
```

## 📌 Stuck?

Walk the line one character at a time with a `StringBuilder` for the field you're building and a `boolean inQuotes`:

- Not in quotes: a `"` starts quotes; the delimiter ends the field (add it to the list, then `setLength(0)`); anything else is added to the field.
- In quotes: a `"` followed by another `"` adds one `"` and skips the second (`i++`); a lone `"` ends the quotes; anything else, the delimiter included, is added to the field.
- After the loop, add the last field. If you're still in quotes, the line was broken.
