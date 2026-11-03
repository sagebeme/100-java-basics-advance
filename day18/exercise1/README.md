# Exercise 1: A shape garden

Draw squares, rectangles, circles, ovals, lines and polygons on a JavaFX `Canvas`, in different colours.

## Requirements
- `Shapes.java` holds the maths, with no JavaFX, so it can be tested:
  - `regularPolygon(cx, cy, radius, sides)`: the corners of a regular polygon, the first corner straight up, as `{xs, ys}`
  - `centredRectangle(cx, cy, w, h)`: the top-left corner of a rectangle centred on a point
  - `circleFits(cx, cy, r, width, height)`: whether a circle fits inside the canvas
- `ShapesDemo.java` draws a "shape garden" with `fillRect`, `fillRoundRect`, `fillOval`, `strokeOval`, `strokeLine` and `fillPolygon`, using `Shapes` for the polygons.

## Files

- `src/Shapes.java`: your code goes here.
- `src/ShapesTest.java`: the tests.
- `src/ShapesDemo.java`: the drawing, already written. It works once `Shapes` does.
- `solution/Shapes.java`: one answer.

## Run it

`ShapesTest` needs no JavaFX, so compile just `Shapes.java` and the test:

```bash
javac -cp "lib/*" -d day18/exercise1/src day18/exercise1/src/Shapes.java day18/exercise1/src/ShapesTest.java
java -cp "day18/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To see the drawing, run `ShapesDemo` with the JavaFX SDK (see LEARNING_PATH.md).

## 📌 Stuck?
- Corner `i` of an `n`-sided polygon is at angle `-π/2 + i × 2π/n`: `x = cx + r·cos(angle)`, `y = cy + r·sin(angle)`. Screen `y` grows downwards, which is why "up" is `-π/2`.
- **Day 18 README**: drawing on a `Canvas` with `GraphicsContext`.
