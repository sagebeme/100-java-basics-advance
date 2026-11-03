# Day 28 - A Pomodoro Timer: Time in JavaFX

> Twenty-five minutes of work, then a break. It's called the Pomodoro technique, after a kitchen timer shaped like a tomato, so today's timer is a tomato too.

## 📖 The story

Wairimu is revising for her KCSE exams in a house where the TV is always on. Her cousin at university swears by the **Pomodoro technique**: work for 25 minutes with your phone out of reach, then rest for 5, and after four rounds take a longer break. Wairimu needs a timer that doesn't live on her phone. You build one:

- a big `25:00` on a ripe red tomato (*nyanya* in Swahili, hence `nyanya.css`);
- **Start** counts down one second at a time;
- **Pause** stops it where it is; **Start** carries on from there;
- **Reset** goes back to `25:00`;
- at zero it stops and says `Time's up!`.

The one piece of pure logic, turning a number of seconds into `MM:SS`, sits in `PomodoroTimerLogic.formatTime`, with tests.

## 🎯 Learning Objectives
- Run code on a schedule with a JavaFX `Timeline` and `KeyFrame`
- Keep the timer's state (`timeLeft`, `isRunning`) in fields
- Start, pause and reset an animation from buttons
- Format seconds as minutes and seconds with `/`, `%` and `String.format("%02d")`
- Test timing logic without waiting in real time, by calling `tick()` yourself

## 🧰 Java you'll use

**A Timeline** runs a `KeyFrame` after a delay; with `INDEFINITE` it repeats forever, on the JavaFX thread, so it can change labels directly. No background thread needed this time:

```java
timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();      // start or carry on
timeline.pause();     // stop, remembering where it was
timeline.stop();      // stop, and start from the beginning next time
```

**Minutes and seconds:**

```java
String.format("%02d:%02d", seconds / 60, seconds % 60)   // 1500 → "25:00", 65 → "01:05"
```

**Testable time.** A test can't wait 25 minutes. So Exercise 1's countdown has a `tick()` method that means "one second has passed". The window calls it once a second; the test calls it 1500 times in a loop and checks the timer rang exactly once.

## 🪜 Steps

1. **[Exercise 1: The invigilator's countdown](exercise1/README.md)** (`exercise1/`): a countdown that pauses, resets and rings exactly once, driven by `tick()` calls so it can be tested without waiting.
2. **[Exercise 2: A revision plan that knows when to rest](exercise2/README.md)** (`exercise2/`): the Pomodoro cycle of work, short breaks and a long break every few sessions, with an enum for the phase.
3. **Project: The Tomato Timer** (`_start/src/PomodoroTimer.java`).
   1. `PomodoroTimerLogic.formatTime`, with its test.
   2. The time label and the three buttons.
   3. The `Timeline`, and `startTimer`, `pauseTimer` and `resetTimer`.
   4. `updateTimer`: take a second off, update the label, and stop with `Time's up!` at zero.
   5. Style it with `nyanya.css`, or your own.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day28/_end/src day28/_end/src/PomodoroTimerLogic.java day28/_end/src/PomodoroTimerLogicTest.java
java -cp "day28/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To open the timer, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day28/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls PomodoroTimer
```

Use `_start/src` for your own version, and `day28/exercise1/src` or `day28/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **Pressing Start twice makes it count down twice as fast**: you made a new `Timeline` on every click. Make one in `start`, and only `play()` it in `startTimer`.
- **It counts past zero into `-1:-59`**: stop the timeline when `timeLeft` reaches 0.
- **`5:3` instead of `05:03`**: use `%02d`, which pads to two digits with a zero.
- **The label doesn't change**: check `updateTimer` is the method in the `KeyFrame`, and that `isRunning` is `true`.

## ✅ Checklist
- [ ] The timer counts down once a second from `25:00`
- [ ] Start, Pause and Reset all work, in any order
- [ ] It stops at zero and says so
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 29 builds a password manager: generate strong passwords and save them to a file.
