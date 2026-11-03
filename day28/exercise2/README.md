# Day 28 - Exercise 2: A revision plan that knows when to rest

Achieng is revising for her university exams in Kisumu and keeps forgetting whether she's due a short break or a long one. The Pomodoro rule: work, short break, work, short break… and after every few work sessions, a long break. Write the plan in `src/PomodoroCycle.java`. It doesn't count seconds; that's Exercise 1's job. It only knows which phase comes next.

| Method | What it does |
|---|---|
| `new PomodoroCycle(work, shortBreak, longBreak, sessionsPerSet)` | Lengths in minutes. Refuse any 0 or less with an `IllegalArgumentException`. Starts on `WORK`. |
| `classic()` (static) | `25, 5, 15, 4`. |
| `phase()` | `WORK`, `SHORT_BREAK` or `LONG_BREAK`. |
| `minutes()` | How long the current phase lasts. |
| `advance()` | The phase is over. After work: count the session, then a long break if that finished a set, otherwise a short one. After any break: work. |
| `completedSessions()` | Work sessions finished so far. |
| `label()` | `"Work 2 of 4"`, `"Short break"` or `"Long break"`. The count starts again at 1 after a long break. |
| `workMinutesDone()` | Sessions finished times the work length. |

The classic day goes: Work 1 of 4, Short break, Work 2 of 4, Short break, Work 3 of 4, Short break, Work 4 of 4, Long break, Work 1 of 4…

## Files

- `src/PomodoroCycle.java`: your code goes here.
- `src/PomodoroCycleTest.java`: the tests.
- `solution/PomodoroCycle.java`: one answer.

```bash
javac -cp "lib/*" -d day28/exercise2/src day28/exercise2/src/*.java
java -cp "day28/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- You only need two pieces of state beyond the settings: the current `Phase` and how many work sessions are done.
- "Finished a set" is `completedSessions % sessionsPerSet == 0`, checked after you count the session.
- For the label, the session you're on is one more than those done in this set: `completedSessions % sessionsPerSet + 1`.
- A `switch` on the phase is the neatest way to write `minutes()` and `label()`.
