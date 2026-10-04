# Day 53 - Exercise 2: CSV that survives a round trip

Data-entry jobs often end with "and send me a spreadsheet". Write two methods in `src/Csv.java`:

- `line(List<String> fields)`: one CSV line. A field containing a comma, a quote or a line break is wrapped in quotes, with quotes inside doubled.
- `parse(String line)`: the fields of one line, undoing exactly that.

The test writes awkward values like `2BR, 3rd floor` and `"Mama" Njeri` and checks that `parse(line(x))` gives `x` back.

## 📌 Stuck?

For `parse`, walk the characters with a `boolean inQuotes`. Inside quotes, `""` means one `"`; outside, a comma ends the field. One answer is in `solution/Csv.java`.
