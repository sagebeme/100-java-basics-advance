// The maths behind drawing shapes, kept out of JavaFX so it can be tested.
public class Shapes {

    // The corners of a regular polygon (triangle, hexagon...) centred on (cx, cy), the first corner straight up.
    // Returns {xs, ys}, ready for GraphicsContext.fillPolygon(xs, ys, sides).
    public static double[][] regularPolygon(double cx, double cy, double radius, int sides) {
        if (sides < 3) throw new IllegalArgumentException("A polygon needs at least 3 sides");
        double[] xs = new double[sides];
        double[] ys = new double[sides];
        for (int i = 0; i < sides; i++) {
            double angle = -Math.PI / 2 + i * 2 * Math.PI / sides;
            xs[i] = cx + radius * Math.cos(angle);
            ys[i] = cy + radius * Math.sin(angle);
        }
        return new double[][]{xs, ys};
    }

    // The top-left corner of a w×h rectangle centred on (cx, cy): JavaFX draws rectangles from their corner.
    public static double[] centredRectangle(double cx, double cy, double w, double h) {
        return new double[]{cx - w / 2, cy - h / 2};
    }

    // True if a circle fits completely inside a canvas.
    public static boolean circleFits(double cx, double cy, double r, double width, double height) {
        return cx - r >= 0 && cy - r >= 0 && cx + r <= width && cy + r <= height;
    }
}
