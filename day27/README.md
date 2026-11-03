# Day 27 - JavaFX Controls: Miles to Kilometres

> Mutua's car came secondhand from the UK, and its speedometer and trip meter are in miles. Today you build him a small window that converts them, with a text field, a button and a label.

## 📖 The story

Lots of cars on Kenyan roads were first registered in the UK or Japan. Mutua's came from the UK, so the dashboard counts in **miles**, while every road sign from Mombasa to Malaba is in **kilometres**. "Nakuru 62 km" means nothing to his trip meter.

He wants a tiny window he can keep open:

```
┌ Mile to Kilometer Converter ┐
│ Miles:                      │
│ [ 38.5            ]         │
│ [ Convert ]                 │
│ Kilometers: 61.95           │
└─────────────────────────────┘
```

Type a number, press **Convert**, read the answer. Type something that isn't a number and it says `Invalid input!` instead of crashing. It's styled like a green highway sign, from a stylesheet, `barabara.css` ("barabara" is Swahili for highway).

On Days 18 to 23 you drew everything yourself on a canvas. Today JavaFX gives you ready-made **controls**: `Label`, `TextField` and `Button`, laid out by a `VBox`.

## 🎯 Learning Objectives
- Build a JavaFX window from controls: `Label`, `TextField`, `Button`
- Lay them out with `VBox` (and know `HBox` and `GridPane`)
- React to a click with an event handler: `button.setOnAction(e -> ...)`
- Read and check what the user typed, and show friendly errors
- Style a window with a CSS stylesheet
- Keep the maths in a plain class you can test without a window

## 🧰 Java you'll use

**Controls and a layout:**

```java
Label inputLabel = new Label("Miles:");
TextField inputField = new TextField();
Button convertButton = new Button("Convert");
Label outputLabel = new Label("Kilometers: 0.0");

VBox root = new VBox(10);                    // 10 pixels between children
root.getChildren().addAll(inputLabel, inputField, convertButton, outputLabel);
stage.setScene(new Scene(root, 300, 200));
```

**An event handler** is a lambda that runs when something happens:

```java
convertButton.setOnAction(e -> {
    try {
        double miles = Double.parseDouble(inputField.getText());
        outputLabel.setText("Kilometers: " + String.format("%.2f", UnitConverterLogic.milesToKilometers(miles)));
    } catch (NumberFormatException ex) {
        outputLabel.setText("Invalid input!");
    }
});
```

**CSS for JavaFX** looks like web CSS, with `-fx-` in front: `.button { -fx-background-color: #ffd23f; }`. Load it with `scene.getStylesheets().add(...)`. Give a control an id (`outputLabel.setId("result")`) to style just that one with `#result`.

**One mile is 1.609 km**, in `UnitConverterLogic.milesToKilometers`.

## 🪜 Steps

1. **[Exercise 1: A currency converter window](exercise1/README.md)** (`exercise1/`): a text field, a button and an event handler, with the money maths and the friendly error messages kept testable.
2. **[Exercise 2: A sign-up form that says everything at once](exercise2/README.md)** (`exercise2/`): checking every field and showing each problem next to its field, and phone numbers typed four different ways.
3. **Project: Miles to Kilometres** (`_start/src/UnitConverter.java`).
   1. `UnitConverterLogic.milesToKilometers`, with its test.
   2. The four controls in a `VBox`.
   3. The button's event handler, including `Invalid input!`.
   4. Copy `barabara.css` from `_end/src` (or write your own) and load it.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day27/_end/src day27/_end/src/UnitConverterLogic.java day27/_end/src/UnitConverterLogicTest.java
java -cp "day27/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To open the window, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day27/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls UnitConverter
```

Use `_start/src` for your own version, and `day27/exercise1/src` or `day27/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **Nothing happens when you click**: the handler was never attached. Check `convertButton.setOnAction(...)`.
- **It crashes on `abc`**: `Double.parseDouble` throws `NumberFormatException`. Catch it and show a message.
- **The stylesheet isn't used**: `getResource("/barabara.css")` looks on the classpath. Run `java` from the folder that holds the CSS, or put the CSS next to your `.class` files.
- **`38,5` is invalid**: Java's `parseDouble` wants a dot. You could `replace(',', '.')` first, for people whose phones type commas.

## ✅ Checklist
- [ ] Built a window from a `Label`, a `TextField` and a `Button`
- [ ] Clicking **Convert** shows kilometres to two decimal places
- [ ] Bad input shows a message instead of crashing
- [ ] Styled the window with CSS
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 28 builds a Pomodoro timer: a countdown that ticks every second with a JavaFX `Timeline`.
