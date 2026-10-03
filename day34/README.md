# Day 34 - Quiz Night: An API Behind a Window

> Friday quiz night, true or false, with questions pulled live from the internet. Build the game, and keep the window from freezing while it waits.

## 📖 The story

Your cousin runs a quiz night at a café in Westlands. The questions come from the [Open Trivia Database](https://opentdb.com/), a free API with thousands of them, and players answer on a laptop at the front. If the café's Wi-Fi drops, the game falls back to twelve Kenya questions that ship with it.

```
┌──────────────────────────────────────────────┐
│ Quiz Night                        [ Score 3 ]│
│ Question 4 of 10                             │
│ ┌──────────────────────────────────────────┐ │
│ │ KENYA                                    │ │
│ │ Lake Turkana is a freshwater lake.       │ │
│ └──────────────────────────────────────────┘ │
│     [   True   ]          [   False   ]      │
└──────────────────────────────────────────────┘
```

## 🎯 Learning Objectives
- Split a GUI program into **logic** (plain Java, tested) and **view** (JavaFX)
- Fetch data from an API and fall back gracefully when it fails
- Decode HTML entities like `&quot;` and `&#039;` that APIs often send
- Run slow work on a background thread with `Task`, so the window never freezes
- Style a JavaFX window with a CSS file
- Use a `PauseTransition` to show feedback before moving on

## 🧰 Java you'll use

**Logic vs view.** `QuizLogic` knows the questions, the score and whose turn it is. `QuizApp` only shows things and passes clicks on. Because `QuizLogic` has no JavaFX in it, you can test every rule without opening a window.

**HTML entities.** The API sends `A cheetah can&#039;t roar.` A regular expression finds each `&...;` and replaces it:

```java
Pattern ENTITY = Pattern.compile("&(#x[0-9a-fA-F]+|#[0-9]+|[a-zA-Z]+);");
// &#039; → Character.toString(39) → '
```

**Background work.** Clicking a button runs on the JavaFX *application thread*, the one that draws the window. A network call on that thread freezes the whole window until it finishes. A `Task` runs elsewhere and hands its result back safely:

```java
Task<List<Question>> task = new Task<>() {
    protected List<Question> call() { return TriviaSource.load(); }   // background thread
};
task.setOnSucceeded(e -> show(task.getValue()));                      // back on the UI thread
new Thread(task).start();
```

**CSS for JavaFX** looks like web CSS with an `-fx-` prefix:

```css
.answer.true { -fx-background-color: #06d6a0; -fx-background-radius: 16; }
```

## 🪜 Steps

1. **Exercise 1: A fallback that always answers** (`exercise1/`). Try a source and fall back to another.
2. **Exercise 2: Shuffle without losing anything** (`exercise2/`). A testable shuffle with a seeded `Random`.
3. **Project: Quiz Night** (`_start/src/`). `QuizApp.java` and `quiz.css` are finished, so concentrate on the logic.
   1. `Html.decode`: named entities (`&quot;`, `&amp;`, `&lt;`, `&gt;`, `&apos;`) and numeric ones (`&#039;`, `&#xe9;`).
   2. `TriviaSource.parse`: check `response_code` is `0`, then build a `Question` for each result.
   3. `TriviaSource.bundled` and `load`: the API first, the bundled `questions.json` on any failure.
   4. `QuizLogic`: `answer`, `isFinished`, `progress` and `verdict`.
   5. Run `QuizApp`, play a round with the Wi-Fi on and off, and see both sources work.

## 🧪 Run it and test it

The tests don't need JavaFX. From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day34/_end/src day34/_end/src/Html.java day34/_end/src/Question.java day34/_end/src/QuizLogic.java day34/_end/src/TriviaSource.java day34/_end/src/QuizLogicTest.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day34/_end/src:lib/*" --scan-classpath
```

To play, you need the [JavaFX SDK](https://gluonhq.com/products/javafx/) (see LEARNING_PATH.md). With it unzipped at `$FX`:

```bash
javac --module-path $FX/lib --add-modules javafx.controls -cp "lib/*" -d day34/_end/src day34/_end/src/*.java
java --module-path $FX/lib --add-modules javafx.controls -cp "day34/_end/src:lib/*" QuizApp
```

## 🆘 Stuck?

- **The window goes white and stops responding at the start**: the API call is running on the application thread. Load questions inside a `Task`.
- **`IllegalStateException: Not on FX application thread`**: you changed a label from the background thread. Do UI changes in `setOnSucceeded`, which runs on the right thread.
- **Questions show `&quot;`**: decode both the question and the category.
- **`NullPointerException` loading `quiz.css` or `questions.json`**: `getResource("/quiz.css")` looks on the classpath. Compile with `-d` into the same `src` folder the files are in, or copy them next to your classes.
- **The API answers `response_code: 5`**: that's "too many requests". The API allows one request every 5 seconds per IP address, so wait a moment.

## ✅ Checklist
- [ ] Decoded named and numeric HTML entities
- [ ] Parsed the API's JSON and checked its response code
- [ ] Fell back to bundled questions when offline
- [ ] Kept every quiz rule in `QuizLogic`, with tests
- [ ] Loaded questions on a background thread
- [ ] Played a full round
- [ ] Committed code to Git

## ➡️ Next

Day 35 is about secrets: API keys, passwords and settings, and how to keep them out of your code and out of Git.
