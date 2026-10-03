# Day 34 - Solution

The finished Quiz Night. `QuizLogicTest.java` covers everything except the window.

Things worth noticing:

- **`QuizApp` has no rules in it.** Every decision (right or wrong, finished or not, what the verdict is) is a `QuizLogic` method. The window only shows the answers.
- **`TriviaSource.load` can't fail.** Any network problem, bad status or odd response falls back to the bundled questions, so the game always starts.
- **The question loads on a `Task`.** The window is drawn straight away and shows "Loading questions…" instead of freezing.
- **`quiz.css` gives the window its own look**: a dark "stage", a lit card with a coloured top edge, green and pink answer buttons, and a card that flashes green or pink for 0.7 seconds after each answer.
