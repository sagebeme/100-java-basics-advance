# Day 29 - Exercise 2: The café's password vault, in a file

Exercise 1 made the passwords. Now the café owner needs somewhere to keep them, so the logins survive the computer being switched off. Write `src/Vault.java`, which keeps a list of `Login`s (`src/Login.java`, already written) and reads and writes them to a text file, one per line:

```
kplc.co.ke|cafe-eldoret|Ug7?mT3x
ecitizen.go.ke|owner|p4N!wq8Z
```

| Method | What it does |
|---|---|
| `Vault.open(path)` | Reads the file. No file yet means an empty vault, not an error. Blank lines are skipped. A line that isn't exactly three parts, or has an empty site, is skipped and counted. An empty password is allowed. |
| `save()` | Writes every login back, in vault order. |
| `put(login)` | Adds it, or replaces the login for the same site (ignoring case) in the same place. A blank site, or a `|` or new line anywhere, would break the file: throw an `IllegalArgumentException`. |
| `get(site)` | An `Optional<Login>`, matching the site ignoring case. |
| `search(term)` | Logins whose site or username contains the term, ignoring case, in vault order. Passwords are never searched. `search("")` lists them all. |
| `remove(site)` | `true` if there was one to remove. |
| `size()`, `skippedLines()` | How many logins; how many lines `open` couldn't read. |

The tests use JUnit's `@TempDir`, a fresh folder for each test that's deleted afterwards, so they never touch your real files.

**This is a lesson in files, not in security.** The passwords sit in the file as plain text. A real vault encrypts them with a key made from a master password, which is a job for a vetted library, never a home-made scheme.

## Files

- `src/Vault.java`: your code goes here.
- `src/Login.java`: done.
- `src/VaultTest.java`: the tests.
- `solution/Vault.java`: one answer.

```bash
javac -cp "lib/*" -d day29/exercise2/src day29/exercise2/src/*.java
java -cp "day29/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- `Files.exists(path)`, `Files.readAllLines(path)` and `Files.write(path, lines)` are all of the file work.
- `split` takes a regular expression, where `|` means "or", so escape it: `line.split("\\|", -1)`. The `-1` keeps empty parts at the end, so `wifi.local|admin|` is three parts, not two.
- In `put`, loop with an index so that you can `logins.set(i, login)` on a match.
- `logins.removeIf(...)` returns whether it removed anything.
