# Day 85 - Portfolio Project: Typing Speed Test App

> How fast do you type, and how many mistakes do you make doing it? Today's app times you on a sentence and scores you in words per minute and accuracy, with the maths kept separate from the clock so it can be tested.

## 📖 The story

Kendi is applying for a data-entry job at a bank in Meru, and the interview includes a typing test: 40 words per minute at 95% accuracy. She wants to practise at home. The app shows a sentence, times her from the moment it appears until she presses Enter, and scores her:

```
Type this sentence as quickly and accurately as you can:

The matatu from Githurai to town was full by six in the morning

> The matatu from Githurai to town was full by six in the morning

Time: 14.2 seconds
Speed: 53.2 WPM
Accuracy: 100.0% (63/63 characters correct)
```

## 🎯 Learning Objectives
- Measure elapsed time with `System.nanoTime()` in a small `Stopwatch` class
- Calculate words per minute with the standard five-characters-per-word rule
- Score accuracy character by character, and penalise text that's too short or too long
- Keep scoring separate from timing, so tests don't depend on a real clock
- Return results as a record, and validate inputs that make no sense
- Handle real-world input: an answer pasted in under a millisecond, or no answer at all

## 🧰 What you'll use

**Words per minute.** Typing tests count every 5 characters (spaces included) as one "word", so long and short words are fair:

```java
double minutes = elapsedMillis / 60000.0;
double wpm = (typedText.length() / 5.0) / minutes;     // 50 characters in 1 minute = 10 WPM
```

**Accuracy.** Compare position by position, and divide by the *longer* of the two texts, so typing half the sentence perfectly scores 50%, not 100%:

```java
int comparedLength = Math.max(targetText.length(), typedText.length());
double accuracy = correct * 100.0 / comparedLength;
```

**A stopwatch with `nanoTime`.** `System.currentTimeMillis()` is the wall clock, which can jump when the computer syncs its time. `System.nanoTime()` only ever moves forward, which is what you want for measuring a duration.

**Timing passed in.** `evaluate(typedText, elapsedMillis)` takes the time as a number. Tests pass exactly 60,000 ms and know the answer; only the app uses the real `Stopwatch`.

## 🪜 Steps

**Your brief** (the `day85/` folder is one finished version: build your own first, then compare).

1. `Stopwatch`: `start`, `stop`, `elapsedMillis`, with errors for reading or stopping it before it starts.
2. `TypingTest(targetText)` and `evaluate`, returning a `TypingResult` record.
3. Tests: the WPM rule, faster typing scoring higher, wrong, partial, short and long answers, an empty target, zero time, and a stopwatch across a real `Thread.sleep`.
4. `TypingTestApp`: a random sentence, the timing, the result, and no crash on empty input.
5. Make it yours: score word by word instead of letter by letter (one missed letter early shouldn't ruin the rest), keep a best score in a file, or build a JavaFX window that highlights mistakes as you type.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day85
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **The WPM is `Infinity`**: the elapsed time was 0. Refuse it in `evaluate`, and in the app count anything under a millisecond as one.
- **Accuracy is 100% after typing only half**: divide by the longer text's length, not the typed length.
- **One missed letter makes everything after it wrong**: that's how position-by-position scoring works. Comparing word by word, or using *edit distance*, is fairer; try it as your extra.
- **The stopwatch test is flaky**: `Thread.sleep(200)` sleeps *at least* 200 ms. Assert a range, not an exact number.

## ✅ Checklist
- [ ] A stopwatch built on `nanoTime`
- [ ] WPM and accuracy that match the standard rules
- [ ] Scoring tested without a real clock
- [ ] A console app that handles odd input
- [ ] Added fairer scoring, a best score, or a window
- [ ] Committed code to Git

## ➡️ Next

Day 86 is an arcade game: Breakout, with a ball, a paddle and a wall of bricks.
