# Day 89 - Portfolio Project: Disappearing Text App

> Writer's block comes from stopping to judge every sentence. This app doesn't let you stop: pause for eight seconds and everything you've written vanishes. It's also the cleanest lesson in this course on testing code that depends on time.

## 📖 The story

Wangari is writing her first novel, set in Nyeri during the 1950s. She has opened the same chapter every morning for a month and deleted it every morning too. A friend tells her about "dangerous writing" apps: keep typing or lose everything. It sounds terrible, and it works, because you can't stop to edit.

She wants a simple window: a big text area, a countdown at the bottom ("Disappears in: 6.4s"), and a **Save** button for when a passage is worth keeping. Saving counts as activity, so it resets the countdown too.

## 🎯 Learning Objectives
- Keep the rule ("idle too long, text disappears") in a class with no user interface
- Inject a `java.time.Clock`, so tests can control time instead of sleeping
- Write a fake clock for tests, and move it forward by exactly the amount you choose
- Work with `Instant` and `Duration`: elapsed time, time remaining, never below zero
- Drive a JavaFX interface with a `Timeline` that ticks every 200 ms
- Listen for every change to a text area with `textProperty().addListener`

## 🧰 What you'll use

**A clock you pass in:**

```java
public DisappearingTextEditor(Clock clock, Duration idleTimeout) { ... }

public boolean isIdle() {
    return Duration.between(lastActivity, clock.instant()).compareTo(idleTimeout) >= 0;
}
```

The app passes `Clock.systemUTC()`. The tests pass their own clock.

**A clock for tests.** `Clock` is an abstract class, so a test can extend it with a time that only moves when told to:

```java
clock.advance(Duration.ofSeconds(4));
assertFalse(editor.isIdle());       // 4 s of a 5 s timeout
clock.advance(Duration.ofSeconds(1));
assertTrue(editor.isIdle());        // exactly 5 s: gone
```

No `Thread.sleep`, so the tests run in milliseconds and give the same answer every time.

**Two kinds of time in the app.** The `Timeline` ticks every 200 ms to *check* the editor; the editor decides, from the clock, whether time is up. The ticker only asks the question.

```java
Timeline ticker = new Timeline(new KeyFrame(Duration.millis(200), e -> {
    if (editor.clearIfIdle()) textArea.setText("");
    countdownLabel.setText(String.format("Disappears in: %.1fs", ...));
}));
ticker.setCycleCount(Timeline.INDEFINITE);
```

(JavaFX has its own `Duration` class; the editor uses `java.time.Duration`. Watch the imports.)

## 🪜 Steps

**Your brief** (the `day89/` folder is one finished version: build your own first, then compare).

1. `SavedEntry(content, savedAt)` and `DisappearingTextEditor` with `setContent`, `isIdle`, `timeUntilDisappears`, `clearIfIdle`, `save` and `clear`.
2. A `MutableClock` in the tests.
3. Tests: idle just before and at the timeout, typing resetting the clock, the countdown never going negative, saving, saving nothing, and saved text surviving.
4. `DisappearingTextApp`: the text area, countdown, saved count and Save button, with an 8-second timeout.
5. Make it yours: saved passages are only kept in memory, so write them to a file when you save; or fade the text out over the last few seconds; or add a word-count goal.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day89
mvn test           # the rules, no window and no waiting
mvn javafx:run     # write, if you dare
```

## 🆘 Stuck?

- **Tests take seconds and sometimes fail**: they're sleeping on the real clock. Inject a `Clock` and use a fake one.
- **`incompatible types: javafx.util.Duration cannot be converted to java.time.Duration`**: two classes share the name. Import one and write the other in full.
- **The text disappears while I'm typing**: `setContent` must be called on every change, so the last activity keeps moving. Check the listener is attached.
- **The countdown shows `-0.2s`**: clamp the remaining time at zero.
- **Saved passages are gone after closing the app**: they're only in a list in memory. Saving them to a file is your extra.

## ✅ Checklist
- [ ] The rule in a class with an injected clock
- [ ] Tests with a fake clock, and no sleeping
- [ ] A window with a live countdown and Save
- [ ] Saved passages written to a file, or another extra
- [ ] Committed code to Git

## ➡️ Next

Day 90 turns a PDF into speech, so you can listen to a document instead of reading it.
