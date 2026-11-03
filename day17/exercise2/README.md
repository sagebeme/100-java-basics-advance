# Day 17 - Exercise 2: A quiz brain you can test

Today's main project asks each question with a `Scanner` inside `QuizBrain`, so the only way to test it is to sit and type. Pull the rules out into a class with no keyboard and no printing: `src/QuizBrain.java`. A real quiz app (or a test) feeds it the replies.

| Part | What it does |
|---|---|
| `new QuizBrain(questions)` | An empty list throws `IllegalArgumentException`: a quiz with no questions is a bug. |
| `hasMore()` | Are there questions left to ask? |
| `next()` | Moves to the next question and returns it. When there are none left, throws `IllegalStateException`. |
| `answer(reply)` | Marks the current question, adds 1 to the score if right, and returns whether it was right. Each question can be answered once: answering before the first `next()`, or twice, throws `IllegalStateException`. |
| `score()` | Right answers so far. |
| `scoreLine()` | `"2/3 (67%)"`: right answers out of the questions *answered so far*, with the percentage rounded. Before any answer, `"0/0"`. |

`Question.java` is Exercise 1's answer, already done. Use its `checkAnswer`.

Then, in `main`, run three questions with the replies `t`, `false`, `t` and print the score line after each.

## Files

- `src/QuizBrain.java`: your code goes here.
- `src/Question.java`: done, from Exercise 1.
- `src/QuizBrainTest.java`: the tests.
- `solution/QuizBrain.java`: one answer.

```bash
javac -cp "lib/*" -d day17/exercise2/src day17/exercise2/src/*.java
java -cp "day17/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Two fields do most of the work: `int asked` (how many times `next()` has run) and `int answered`. A question can be answered when `answered < asked`.
- The current question is `questions.get(asked - 1)`.
- Rounding: `Math.round(100.0 * right / answered)`. The `100.0` makes it a `double` division, so 2/3 isn't 0.
- Copy the list in the constructor (`List.copyOf(questions)`), so someone changing their list later can't change your quiz.
