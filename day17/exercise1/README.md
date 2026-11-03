# Day 17 - Exercise 1: One question, many ways to answer

The matatu quiz night at the stage runs on True/False questions. People type answers any way they like: `true`, ` TRUE `, `t`, `F`. Write `src/Question.java` so all of those count.

| Part | What it does |
|---|---|
| `new Question(text, answer)` | `answer` must be `"True"` or `"False"` (any case). Anything else throws `IllegalArgumentException`: a question nobody can get right is a bug. |
| `getText()` | The question. |
| `checkAnswer(reply)` | `true` if the reply means the right answer. Spaces around it and capitals don't matter, and `t`/`f` are short for True/False. A blank or unknown reply is wrong, not an error. A `null` reply is wrong too. |
| `toString()` | `Q: Route 46 goes to Kawangware (True/False)` |

Then, in `main`, make the Route 46 question (its answer is True) and print it and whether `" t "` is right.

## Files

- `src/Question.java`: your code goes here.
- `src/QuestionTest.java`: the tests.
- `solution/Question.java`: one answer.

```bash
javac -cp "lib/*" -d day17/exercise1/src day17/exercise1/src/*.java
java -cp "day17/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Turn every reply into one tidy form first: `reply.strip().toLowerCase()`. Then `"t"` and `"true"` both mean True.
- Store the answer as a `boolean` in the constructor (`answer.equalsIgnoreCase("true")`), after checking it's one of the two words. Comparing booleans is simpler than comparing strings.
