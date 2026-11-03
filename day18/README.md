# Day 18 - Dot Painting: Your First JavaFX Window

> Some of the most expensive paintings ever sold are rows of coloured dots on a white canvas. Today you paint one with JavaFX, in a fraction of a second, and a new one every time you run it.

## 📖 The story

Achieng runs a small print shop on Moi Avenue. A café owner wants framed art for her walls: bright, cheerful, and "like those famous spot paintings by Damien Hirst, but not those exactly". Achieng could paint dots by hand for a week, or she could write a program that paints them:

- an 800 × 800 canvas, primed off-white rather than screen white;
- a grid of dots 20 pixels across, 50 pixels apart;
- each dot a random colour from a palette of soft, chalky colours, the way real paint looks.

Every run is a new painting. She prints the ones she likes.

This is the first day with a **window** instead of a terminal. The window comes from **JavaFX**, Java's toolkit for graphical programs.

## 🎯 Learning Objectives
- Start a JavaFX program: extend `Application` and override `start(Stage)`
- Draw on a `Canvas` with its `GraphicsContext`: `setFill`, `fillRect`, `fillOval`
- Understand screen coordinates: `(0, 0)` is the top-left corner, and `y` grows downwards
- Use nested loops to place things on a grid
- Pick random items from a fixed palette

## 🧰 Java you'll use

**A JavaFX program** has the same shape every time:

```java
public class HirstPainting extends Application {
    @Override
    public void start(Stage stage) {          // JavaFX calls this when the window is ready
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // ... draw with gc ...
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Hirst Painting Generator");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);                           // starts JavaFX, which then calls start
    }
}
```

**Drawing.** Set a colour, then fill a shape. `fillOval(x, y, w, h)` draws inside the box whose top-left corner is `(x, y)`, so to centre a dot on a point, take half its size off both:

```java
gc.setFill(Color.web("#d8584a"));
gc.fillOval(x - DOT_SIZE / 2, y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE);
```

**A grid** is a loop inside a loop: one for the columns, one for the rows.

**JavaFX isn't part of the JDK** (it hasn't been since Java 11). Download the [JavaFX SDK](https://gluonhq.com/products/javafx/) and tell `javac` and `java` where it is, as shown below. The exercises' tests don't need it.

## 🪜 Steps

1. **[Exercise 1: A shape garden](exercise1/README.md)** (`exercise1/`): the corners of any regular polygon, a rectangle centred on a point, and whether a circle fits the canvas.
2. **[Exercise 2: Kitenge patterns](exercise2/README.md)** (`exercise2/`): a checkerboard, repeating stripes and a spiral, each as a rule that says what colour a square is.
3. **Project: Dot Painting** (`_start/src/HirstPainting.java`).
   1. Fill the canvas with an off-white background first.
   2. Make a palette: an array of about a dozen `Color.web(...)` colours. Soft colours look like paint; pure `Color.RED` looks like a screen.
   3. Loop over `x` and `y` from 50 to 750 in steps of 50, and draw a dot at each point in a random palette colour.

## 🧪 Run it and test it

Point `FX` at the `lib` folder of your JavaFX SDK, then:

```bash
FX=~/javafx-sdk-21/lib
cd day18/_end/src
javac --module-path $FX --add-modules javafx.controls HirstPainting.java
java --module-path $FX --add-modules javafx.controls HirstPainting
```

In an IDE, add the SDK's jars as a library and the same two options as VM options. The project has no tests: look at the window. The exercises do have tests, and they run without JavaFX (see each exercise's README).

## 🆘 Stuck?

- **`package javafx.application does not exist`**: JavaFX isn't on the module path. Check `--module-path` points at the SDK's `lib` folder, and that `--add-modules javafx.controls` is there for both `javac` and `java`.
- **The dots are cut off at the edges**: the loop starts at 0. Start at `SPACING` and stop before `800 - SPACING`.
- **Every dot is the same colour**: you picked the colour once, outside the loops. Pick it inside the inner loop.
- **Dots aren't centred on the grid**: `fillOval` takes the top-left corner. Subtract half the dot size.

## ✅ Checklist
- [ ] Installed the JavaFX SDK and opened a window
- [ ] Drew on a `Canvas` with `GraphicsContext`
- [ ] Placed dots on an evenly spaced grid with nested loops
- [ ] Each run paints a different painting
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 19 makes things move: four turtles race across the screen, and you meet inheritance.
