# Day 27 - Exercise 1: A currency converter window

A tourist at a hotel desk in Diani wants to know what a price in shillings is in dollars. `src/ConverterApp.java` is a small JavaFX window, already written: labels, a text field, a button, an event handler, and two layouts (a `VBox` for the page, with an `HBox` row for the field and button). Read it to see how they fit together. Your job is the logic it calls, in `src/ConverterLogic.java`, kept out of JavaFX so it can be tested.

## Requirements
- `ConverterLogic.kesToUsd(text, rate)` (no JavaFX, tested): `"1,500"` → `"KES 1,500 = USD 11.58"`; blank → `"Type an amount in shillings."`; anything that isn't a positive amount → a message saying so.
- `ConverterApp` (done): typing an amount and pressing **Convert** (or Enter) shows the result in a label.

## Files

- `src/ConverterLogic.java`: your code goes here.
- `src/ConverterLogicTest.java`: the tests.
- `src/ConverterApp.java`: the window, already written. It works once `ConverterLogic` does.
- `solution/ConverterLogic.java`: one answer.

## Run it

`ConverterLogicTest` needs no JavaFX, so compile just the logic and the test:

```bash
javac -cp "lib/*" -d day27/exercise1/src day27/exercise1/src/ConverterLogic.java day27/exercise1/src/ConverterLogicTest.java
java -cp "day27/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

Run `ConverterApp` with the JavaFX SDK (see LEARNING_PATH.md).

## 📌 Stuck?
- `button.setOnAction(e -> label.setText(...))` is the event handler.
- `button.setDefaultButton(true)` makes Enter press it.
- Divide money with `BigDecimal.divide(rate, 2, RoundingMode.HALF_UP)` (Day 36).
