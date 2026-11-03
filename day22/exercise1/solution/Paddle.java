public class Paddle {

    private final int x;
    private int top;
    private final int height;
    private final int courtHeight;

    public Paddle(int x, int top, int height, int courtHeight) {
        if (height <= 0 || height > courtHeight) {
            throw new IllegalArgumentException("A paddle must be taller than 0 and fit the court");
        }
        this.x = x;
        this.height = height;
        this.courtHeight = courtHeight;
        this.top = clamp(top);
    }

    public int getX() {
        return x;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return top + height;
    }

    public void moveUp(int pixels) {
        top = clamp(top - pixels);
    }

    public void moveDown(int pixels) {
        top = clamp(top + pixels);
    }

    public boolean covers(int y) {
        return y >= top && y <= top + height;
    }

    // -1.0 at the top edge, 0.0 in the middle, 1.0 at the bottom edge.
    public double hitOffset(int y) {
        if (!covers(y)) {
            throw new IllegalArgumentException("The ball at " + y + " missed the paddle");
        }
        double middle = top + height / 2.0;
        return (y - middle) / (height / 2.0);
    }

    // Keeps the whole paddle inside the court.
    private int clamp(int newTop) {
        return Math.max(0, Math.min(courtHeight - height, newTop));
    }
}
