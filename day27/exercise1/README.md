# Exercise 1: A currency converter window

Build a small JavaFX window with labels, a text field, a button, an event handler, and two layouts: a `VBox` for the page, with an `HBox` row for the field and button.

## Requirements
- `ConverterLogic.kesToUsd(text, rate)` (no JavaFX, tested): `"1,500"` → `"KES 1,500 = USD 11.58"`; blank → `"Type an amount in shillings."`; anything that isn't a positive amount → a message saying so.
- `ConverterApp`: typing an amount and pressing **Convert** (or Enter) shows the result in a label.

## Run it
`ConverterLogicTest.java` needs no JavaFX. Run `ConverterApp` with the JavaFX SDK (see LEARNING_PATH.md).

## 📌 Refer to (when stuck)
- `button.setOnAction(e -> label.setText(...))` is the event handler.
- `button.setDefaultButton(true)` makes Enter press it.
- Divide money with `BigDecimal.divide(rate, 2, RoundingMode.HALF_UP)` (Day 36).
