# Day 29 - A Password Manager: Forms, Lists and a File

> Kamau's cyber café has a dozen logins (the router, the printer, the M-Pesa till, three email accounts) and they're all on a sticky note. Today you replace the sticky note.

## 📖 The story

Every cyber café has a sticky note like Kamau's, stuck to the side of the counter monitor, with `admin` and `kamau123` on it for half the systems in the shop. Today's JavaFX window keeps them instead:

- three fields: **Website**, **Username**, **Password**;
- **Generate** fills in a strong 16-character password;
- **Save** adds the login to a list on screen and to the file `passwords.txt`;
- the next time the program starts, it reads the file back, so nothing is lost.

Each login is one line in the file, with the three parts separated by `|`:

```
router.local|admin|q7#Rk2!vLm9&Xc4T
mpesa-till|kamau|J8f%Tq2@wNz5!bHc
```

The format lives in `PasswordManagerLogic`: `formatEntry` writes a line, `parseEntry` reads one back (or returns `null` for a broken line), and `generatePassword(length, random)` makes the passwords. The tests check all three.

**A warning that matters.** This file stores passwords as plain text, and anyone who opens it can read them. That's fine for learning file I/O, and it's what Day 30 starts to improve, but real password managers encrypt the whole file with a master password. Never commit it: `.gitignore` already keeps `passwords.txt` out of Git.

## 🎯 Learning Objectives
- Build a form from `Label`s, `TextField`s and `Button`s, and show saved items in a `ListView`
- Save data to a text file in a simple format, and load it back at start-up
- Design a line format with a separator, and parse it safely
- Generate passwords with `SecureRandom`, and know why `Random` isn't good enough
- Keep a small class inside another (`PasswordEntry`) to hold one record

## 🧰 Java you'll use

**Writing every entry:**

```java
try (PrintWriter writer = new PrintWriter(new FileWriter("passwords.txt"))) {
    for (PasswordEntry entry : entries) {
        writer.println(PasswordManagerLogic.formatEntry(entry.website, entry.username, entry.password));
    }
}
```

**Reading them back.** `|` means "or" in a regular expression, so `split` needs it escaped: `line.split("\\|")`. Check you got exactly three parts before using them.

**`Random` vs `SecureRandom`.** `new Random()` is fine for dice in a game, but its next numbers can be worked out from the ones it has already given. `new SecureRandom()` can't be predicted. It's also a `Random`, so `generatePassword(16, new SecureRandom())` works, and a test can pass `new Random(7)` to get the same password every time.

**A `ListView`** shows a list of strings: `passwordList.getItems().setAll(...)`.

## 🪜 Steps

1. **[Exercise 1: Passwords for the cyber café staff](exercise1/README.md)** (`exercise1/`): a generator that always includes every chosen kind of character, shuffled, plus a strength checker.
2. **[Exercise 2: The café's password vault, in a file](exercise2/README.md)** (`exercise2/`): save, load, search, replace and remove logins in a text file, and skip broken lines without losing count.
3. **Project: The Café Password Manager** (`_start/src/PasswordManager.java`).
   1. `PasswordManagerLogic`: `generatePassword`, `formatEntry` and `parseEntry`, with their tests.
   2. The form, the buttons and the `ListView`.
   3. **Generate**: a 16-character password from `SecureRandom`.
   4. **Save**: refuse empty fields, add the entry, update the list, write the file, clear the form.
   5. At start-up, load `passwords.txt` if it exists.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day29/_end/src day29/_end/src/PasswordManagerLogic.java day29/_end/src/PasswordManagerLogicTest.java
java -cp "day29/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To open the window, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day29/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls PasswordManager
```

Use `_start/src` for your own version, and `day29/exercise1/src` or `day29/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **Every line splits into single characters**: you wrote `split("|")`. Escape it: `split("\\|")`.
- **Yesterday's logins are gone**: you load the file before the window is built, or not at all. Call `loadPasswords()` in `start`, then fill the list.
- **The file only ever holds the last login**: `new FileWriter(name)` replaces the file. Either write every entry each time (as the project does) or append with `new FileWriter(name, true)`.
- **A password with `|` in it breaks the file**: leave `|` out of the characters you generate from, and refuse it in typed input. Exercise 2 does this.

## ✅ Checklist
- [ ] Generate makes a 16-character password with `SecureRandom`
- [ ] Save refuses empty fields, and adds the login to the list and the file
- [ ] Logins come back when the program restarts
- [ ] `passwords.txt` is not in Git
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 30 improves the password manager with exceptions, a search box, and JSON instead of `|`.
