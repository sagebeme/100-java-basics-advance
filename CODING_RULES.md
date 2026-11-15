# How We Write Code in This Course

Ten habits for every day, from Day 1 to Day 100. They're how working programmers keep code easy to trust, and every day's tests and checks assume them.

1. **Say what "done" means before you code.** Read the whole day's README and the exercise README, then write one line for yourself: "done is when `countRed` returns 3 for that example and the tests pass." If a step could mean two things, ask (an issue, a friend, the stuck notes) before you guess.

2. **Write only what the task asks.** No extra menu options, settings or classes "for later". If you think of a good extra, note it down and do it after the day is finished.

3. **Keep it small and clear.** One method does one job; if a method grows past about 40 lines, see whether it splits into two. Before you finish, delete anything you added and didn't need: unused variables, old attempts, `DEBUG` prints.

4. **Change only what the task needs, and match what's there.** In a `_start/` file, fill in the `TODO`s; don't rename, reorder or reformat the rest. Use the same naming and layout as the code around you.

5. **Bugs: make a test fail first, then fix it.** Run the tests (or add one) and watch it fail because of the bug. Then fix the smallest thing you can, and run *all* the tests again. Day 13 practises exactly this.

6. **Before you call it done, run the checks:**
   - The tests. Days 1–46 and the plain exercises: the two `javac` / `java -jar lib/junit-…` commands in the main [README](README.md#-how-to-run-tests). Maven days: `mvn test` in the project's folder.
   - `javac` is your type checker: fix every error, and read its warnings.
   - `tools/check-style.sh dayNN` looks for hidden errors (empty `catch`, `printStackTrace()`, a few real bugs).
   - There's no automatic formatter in this course: keep your code's layout like the file you're in.

7. **Comments say *why*; Javadoc says what a method takes, gives back and when it fails.**
   ```java
   /** Kilograms to tonnes, keeping the decimals. Throws IllegalArgumentException if kg is negative. */
   static double toTonnes(int kg) { ... }
   ```
   Not `// add 1 to i` above `i++`: the code already says that.

8. **Don't hide errors.** Catch the specific exception you can do something about, and tell the user (a message, an `Alert`, a status label). Otherwise let it go up with `throws`. Never leave a `catch` empty, never catch `Exception` or `Throwable` "just in case", and don't use `e.printStackTrace()` as handling.
   ```java
   catch (IOException e) { }                                         // bad: the failure vanishes
   catch (IOException e) { status.setText("Couldn't save: " + e.getMessage()); }   // good
   ```

9. **Tests check results; they don't print them.** A test uses `assertEquals`, `assertTrue` or `assertThrows`, and fails when the code is wrong. A `main` that prints the answer is a demo, not a test.

10. **Commit clean, with a clear message.** Never commit secrets (`.env`, `config.local.properties`, real API keys; Day 35 shows how) or generated files (`*.class`, `target/`, `passwords.json`): the `.gitignore` already lists them, so add files by name (`git add day13/_start/src/Weighbridge.java`) rather than `git add .`. Say what changed and why: `fix(day13): compare factory names with equals, not ==`, not `update`.
