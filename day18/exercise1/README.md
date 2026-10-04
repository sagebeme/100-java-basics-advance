# Exercise 1: A shape garden

Draw squares, rectangles, circles, ovals, lines and polygons on a JavaFX `Canvas`, in different colours.

## Requirements
- `Shapes.java` holds the maths, with no JavaFX, so it can be tested:
  - `regularPolygon(cx, cy, radius, sides)`: the corners of a regular polygon, the first corner straight up, as `{xs, ys}`
  - `centredRectangle(cx, cy, w, h)`: the top-left corner of a rectangle centred on a point
  - `circleFits(cx, cy, r, width, height)`: whether a circle fits inside the canvas
- `ShapesDemo.java` draws a "shape garden" with `fillRect`, `fillRoundRect`, `fillOval`, `strokeOval`, `strokeLine` and `fillPolygon`, using `Shapes` for the polygons.

## Run it
`ShapesTest.java` needs no JavaFX: compile `Shapes.java` and the test with the JUnit jar, as on other days. To see the drawing, run `ShapesDemo` with the JavaFX SDK (see LEARNING_PATH.md).

## 📌 Refer to (when stuck)
- Corner `i` of an `n`-sided polygon is at angle `-π/2 + i × 2π/n`: `x = cx + r·cos(angle)`, `y = cy + r·sin(angle)`. Screen `y` grows downwards, which is why "up" is `-π/2`.
- **Day 18 README**: drawing on a `Canvas` with `GraphicsContext`.
