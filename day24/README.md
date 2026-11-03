# Day 24 - Mail Merge: Harambee Invitations From Two Files

> The Wambui family is holding a harambee for school fees, and fifty people need a letter with their own name on it. Nobody is typing fifty letters.

## 📖 The story

Wambui got into a good secondary school, and the fees are more than her parents can pay alone. So the family is holding a **harambee**: friends, neighbours and church members come together, eat, sing and contribute. Her uncle has written the invitation once, with `[name]` where each guest's name goes, and he has a list of guests, one per line. Your program does the rest:

```
$ java MailMerge
Mail merge complete! Created 5 files.

$ cat output_2.txt
Dear Mwalimu Otieno,

You are warmly invited to the harambee for Wambui's school fees,
on Saturday 14 November at 2pm, at the PCEA church hall in Kikuyu.
...
```

That's a **mail merge**: one template plus one list gives one letter per person. Offices do it every day for invitations, payslips and reminders.

`template.txt` and `names.txt` are in `_start/src` and `_end/src`. Change them and run it again.

## 🎯 Learning Objectives
- Read a text file line by line, and all at once
- Write text to a new file, and append to an existing one
- Understand relative paths: where a program looks for `names.txt`
- Handle `FileNotFoundException` and `IOException` with clear messages
- Close files properly, ideally with try-with-resources

## 🧰 Java you'll use

**Reading.** The project uses `Scanner` on a `File`. The newer `Files` class does the same in one line:

```java
List<String> names = Files.readAllLines(Path.of("names.txt"));
String template = Files.readString(Path.of("template.txt"));
```

**Writing.** `Files.writeString` creates or replaces a file; with `StandardOpenOption.APPEND` it adds to the end instead:

```java
Files.writeString(Path.of("output_1.txt"), letter);
```

**Replacing.** `template.replace("[name]", name)` replaces *every* `[name]`, not just the first, and returns a new string: the template itself doesn't change, so you can use it again for the next guest.

**Relative paths** are relative to the folder you ran `java` from, not the folder the `.java` file is in. Run from `_end/src` and it finds `names.txt` there.

**Try-with-resources** closes a file even when something goes wrong:

```java
try (FileWriter writer = new FileWriter("output_1.txt")) {
    writer.write(letter);
}   // closed here, always
```

## 🪜 Steps

1. **[Exercise 1: Reading Mama Njeri's shop log](exercise1/README.md)** (`exercise1/`): counting lines and words, finding a word with its line number, and adding up the sales, from a file read with `Files.readAllLines`.
2. **[Exercise 2: Receipts that line up](exercise2/README.md)** (`exercise2/`): writing files that never overwrite each other, appending to a day book, and lining up columns with `String.format`.
3. **Project: Mail Merge** (`_start/src/MailMerge.java`).
   1. `merge(template, name)`: replace every `[name]` with the name. The tests check this.
   2. In `main`, read `template.txt` into one string.
   3. Read `names.txt` and, for each name, write `output_1.txt`, `output_2.txt` and so on.
   4. Print how many letters you made, and a clear message if a file is missing.

## 🧪 Run it and test it

```bash
cd day24/_end/src
javac MailMerge.java
java MailMerge
```

The letters it writes (`output_*.txt`) are kept out of Git by `.gitignore`. To test your own version, copy `_end/src/MailMergeTest.java` into `_start/src`, then from the repo root:

```bash
javac -cp "lib/*" -d day24/_start/src day24/_start/src/*.java
java -cp "day24/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

For an exercise, use `day24/exercise1/src` or `day24/exercise2/src` in both commands. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`File not found: template.txt`**: you ran `java` from another folder. `cd` into the folder that holds the text files first.
- **Only the first `[name]` is replaced**: that's `replaceFirst`. Use `replace`.
- **Every letter has the first guest's name**: you replaced inside the template itself and kept the result. Replace into a new variable each time.
- **The letters are empty**: the `FileWriter` was never closed, so nothing was written. Use try-with-resources.
- **There's an extra empty letter**: `names.txt` ends with a blank line. Skip names that are blank after `trim()`.

## ✅ Checklist
- [ ] Read the template and the names from files
- [ ] Wrote one letter per guest, with every `[name]` replaced
- [ ] A missing file gives a clear message, not a stack trace
- [ ] Closed every file
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git (but not the letters)

## ➡️ Next

Day 25 reads a CSV file and turns it into a guessing game.
