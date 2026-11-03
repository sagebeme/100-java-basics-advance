// The maths behind drawing shapes, kept out of JavaFX so it can be tested.
public class Shapes {

    // The corners of a regular polygon (triangle, hexagon...) centred on (cx, cy), the first corner straight up.
    // Returns {xs, ys}, ready for GraphicsContext.fillPolygon(xs, ys, sides).
    public static double[][] regularPolygon(double cx, double cy, double radius, int sides) {
        // TODO: refuse fewer than 3 sides; then work out each corner's x and y
        return new double[][]{new double[0], new double[0]};
    }

    // The top-left corner of a w×h rectangle centred on (cx, cy): JavaFX draws rectangles from their corner.
    public static double[] centredRectangle(double cx, double cy, double w, double h) {
        // TODO
        return new double[]{0, 0};
    }

    // True if a circle fits completely inside a canvas.
    public static boolean circleFits(double cx, double cy, double r, double width, double height) {
        // TODO: all four edges of the circle inside the canvas
        return false;
    }
}
