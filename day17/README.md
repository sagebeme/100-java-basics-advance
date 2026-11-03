# Day 17 - Quiz Night: Objects That Work Together

> Every Thursday the tech hub in Kilimani runs a quiz night. This week's round is true-or-false about Java, and you're writing the program that asks the questions.

## 📖 The story

Wanjiku hosts quiz night. Last week she read the questions from her phone and kept the score on a napkin, and two teams argued about it for twenty minutes. This week the quiz runs itself:

```
Welcome to the Java Quiz!

Q1. Java is a compiled language
Your answer (True/False): true
Correct!

Q2. Java supports multiple inheritance
Your answer (True/False): True
Wrong!
...
=== Quiz Complete ===
Your final score: 4/5
```

The program is three small classes, and each one only knows its own job:

- a `Question` holds its text and its answer, and can check a guess;
- a `QuizBrain` holds the list of questions, knows which one is next, and keeps the score;
- `QuizApp` writes the questions and runs the loop.

## 🎯 Learning Objectives
- Design a program as several classes that each have one responsibility
- Keep objects in a `List` and walk through them in order
- Keep track of progress (the current question, the score) inside an object
- Compare answers without caring about capitals, with `equalsIgnoreCase`
- Separate the rules of a program from its input and output, so the rules can be tested

## 🧰 Java you'll use

**A list of objects.** Each element is a whole `Question`, with its own text and answer:

```java
List<Question> questions = new ArrayList<>();
questions.add(new Question("Java uses garbage collection", "True"));
Question first = questions.get(0);
```

**An object that remembers where it is.** `QuizBrain` has `currentQuestion` and `score` fields. Every call to `nextQuestion()` moves `currentQuestion` on by one, so the loop in `main` stays tiny:

```java
while (quiz.stillHasQuestions()) {
    quiz.nextQuestion();
}
quiz.showFinalScore();
```

**Asking the object, not reading its data.** `question.checkAnswer(guess)` is better than `question.getAnswer().equals(guess)`: the rule for what counts as right lives in one place, inside `Question`.

## 🪜 Steps

1. **[Exercise 1: One question, many ways to answer](exercise1/README.md)** (`exercise1/`): a `Question` that refuses an impossible answer and accepts `true`, ` TRUE ` and `t` alike.
2. **[Exercise 2: A quiz brain you can test](exercise2/README.md)** (`exercise2/`): the quiz rules pulled out of the `Scanner` loop, so a test can play the quiz: questions in order, one answer each, and a score line.
3. **Project: Quiz Night** (`_start/src/`).
   1. `Question`: the text and the answer, `getText()`, and `checkAnswer(guess)` that ignores capitals.
   2. `QuizBrain`: the list, `stillHasQuestions()`, `nextQuestion()` (print it, read an answer, say `Correct!` or `Wrong!`, update the score) and `showFinalScore()`.
   3. `QuizApp.main`: five true-or-false questions, then the loop. Write your own questions if you like.

## 🧪 Run it and test it

```bash
cd day17/_end/src
javac *.java
java QuizApp
```

To test your own `Question`, copy `_end/src/QuestionTest.java` into `_start/src`, then from the repo root:

```bash
javac -cp "lib/*" -d day17/_start/src day17/_start/src/*.java
java -cp "day17/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

For an exercise, use `day17/exercise1/src` or `day17/exercise2/src` in both commands. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`IndexOutOfBoundsException` after the last question**: `stillHasQuestions()` should be `currentQuestion < questions.size()`, not `<=`.
- **"true" is marked wrong**: compare with `equalsIgnoreCase`, not `==` or `equals`.
- **Every question repeats Q1**: `nextQuestion()` must add one to `currentQuestion` at the end.
- **Hard to test because of the `Scanner`**: that's the point of Exercise 2. Pass the answer in as a parameter, and keep the reading in `main`.

## ✅ Checklist
- [ ] `Question` checks answers without caring about capitals
- [ ] `QuizBrain` asks every question once, in order, and keeps the score
- [ ] The final score is shown as `score/total`
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 18 opens a window for the first time: you'll paint a grid of coloured dots with JavaFX.
