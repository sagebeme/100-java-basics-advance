public class Paddle {

    // TODO: private fields: x, top, height and the court's height

    public Paddle(int x, int top, int height, int courtHeight) {
        // TODO: refuse a height of 0 or less, or taller than the court; keep the paddle inside the court
    }

    public int getX() {
        // TODO
        return -1;
    }

    public int getTop() {
        // TODO
        return -1;
    }

    public int getBottom() {
        // TODO
        return -1;
    }

    public void moveUp(int pixels) {
        // TODO: never past the top of the court
    }

    public void moveDown(int pixels) {
        // TODO: never past the bottom of the court
    }

    public boolean covers(int y) {
        // TODO: is y between the paddle's top and bottom, edges included?
        return false;
    }

    // -1.0 at the top edge, 0.0 in the middle, 1.0 at the bottom edge.
    public double hitOffset(int y) {
        // TODO: refuse a y the paddle doesn't cover
        return 0;
    }
}
