# Day 30 - Exceptions and JSON: A Password Manager That Fails Politely

> Yesterday's password manager worked until something went wrong. Today it gets a search box, saves real JSON, and says exactly what went wrong instead of crashing or keeping quiet.

## 📖 The story

Kamau has used the café's password manager from Day 29 for a week, and he has three complaints:

1. He has thirty logins now and can't find anything. He wants to type `mpesa` and see only the matching ones.
2. His nephew opened `passwords.txt` in Notepad, "tidied it up", and the program silently lost half the logins.
3. When something fails, he'd like to be told *what*.

Today's version fixes all three:

- a **Search** box that matches the website or the username, ignoring capitals (`ImprovedPasswordManagerLogic.matches`, with tests);
- logins saved as **JSON** in `passwords.json`, a format every language can read;
- a status line that says what happened: `Loaded 12 passwords`, `No saved passwords yet`, `passwords.json is damaged (...). Fix or move it, then restart.`

```json
[
  {
    "website": "mpesa-till",
    "username": "kamau",
    "password": "J8f%Tq2@wNz5!bHc"
  }
]
```

And when the file is damaged, the program refuses to save over it, so a bad edit can be repaired instead of wiped out.

## 🎯 Learning Objectives
- Throw, catch and handle exceptions on purpose, with `try`, `catch` and `finally`
- Know checked exceptions (`IOException`) from unchecked ones (`IllegalArgumentException`)
- Catch the most specific exception first, and give each its own message
- Write your own exception class
- Read and write JSON with the `org.json` library, and understand its structure: objects, arrays, strings and numbers

## 🧰 Java you'll use

**Specific catches, in order.** `NoSuchFileException` is a kind of `IOException`, so it must come first, or the general one would catch everything:

```java
try {
    JSONArray saved = new JSONArray(Files.readString(Path.of("passwords.json")));
    ...
} catch (NoSuchFileException e) {
    statusLabel.setText("No saved passwords yet");        // normal on the first run
} catch (JSONException e) {
    statusLabel.setText("passwords.json is damaged (" + e.getMessage() + ")");
} catch (IOException e) {
    statusLabel.setText("Couldn't read passwords.json: " + e.getMessage());
}
```

Avoid `catch (Exception e)`: it hides bugs you'd want to see, and you can't give a useful message for "anything at all".

**JSON with `org.json`.** Its jar is in `lib/`. `JSONObject` is `{ ... }`, and `JSONArray` is `[ ... ]`:

```java
JSONArray all = new JSONArray();
all.put(new JSONObject().put("website", "mpesa-till").put("username", "kamau").put("password", pw));
Files.writeString(Path.of("passwords.json"), all.toString(2));   // 2 = indent, so people can read it
```

The library escapes quotes and backslashes for you. Exercise 2 shows what that takes, by writing a small JSON reader and writer by hand.

## 🪜 Steps

1. **[Exercise 1: The M-Pesa agent's float](exercise1/README.md)** (`exercise1/`): a checked exception of your own, `IllegalArgumentException` and `NumberFormatException`, each caught on purpose, with a `finally` that counts every customer.
2. **[Exercise 2: Reading and writing JSON by hand](exercise2/README.md)** (`exercise2/`): write flat JSON with proper escapes and read it back one character at a time, with errors that say where the text went wrong.
3. **Project: The Improved Password Manager** (`_start/src/ImprovedPasswordManager.java`).
   1. `ImprovedPasswordManagerLogic.matches(website, username, term)`, with its test.
   2. The search box and button.
   3. `saveToFile()`: write every login as a JSON array. Return whether it worked.
   4. `loadPasswords()`: read it back, with a different message for a missing file, a damaged file and any other read error. Don't save over a damaged file.
   5. A status label for every message.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day30/_end/src day30/_end/src/ImprovedPasswordManagerLogic.java day30/_end/src/ImprovedPasswordManagerLogicTest.java
java -cp "day30/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To open the window, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18). The JSON jar in `lib/` goes on the class path:

```bash
cd day30/_end/src
javac --module-path $FX --add-modules javafx.controls -cp "../../../lib/*" *.java
java --module-path $FX --add-modules javafx.controls -cp ".:../../../lib/*" ImprovedPasswordManager
```

Use `_start/src` for your own version, and `day30/exercise1/src` or `day30/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`. `passwords.json` is kept out of Git by `.gitignore`.

## 🆘 Stuck?

- **`exception NoSuchFileException has already been caught`**: a catch for `IOException` comes before it. Put the more specific catch first.
- **`package org.json does not exist`**: `lib/*` isn't on the class path. Add `-cp` as above.
- **`JSONObject["password"] not found`**: one login in the file is missing a field. That's a `JSONException`, so the damaged-file message covers it.
- **Search shows nothing for `MPESA`**: compare in lower case, on both sides.
- **"Saved!" appears even when saving failed**: let `saveToFile()` return `false` on failure, and only show success when it returns `true`.

## ✅ Checklist
- [ ] Search matches the website or username, ignoring capitals
- [ ] Logins are saved as JSON and come back on restart
- [ ] A missing file, a damaged file and other read errors each get their own message
- [ ] A damaged file is never saved over
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 31 is the Intermediate capstone: a Swahili flash card app that remembers which words you still need to learn.
