public class Paddle {
    private double y;
    private final double height;
    private final double minY;
    private final double maxY;

    public Paddle(double startY, double height, double minY, double maxY) {
        this.y = startY;
        this.height = height;
        this.minY = minY;
        this.maxY = maxY;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public void moveUp(double amount) {
        y = Math.max(minY, y - amount);
    }

    public void moveDown(double amount) {
        y = Math.min(maxY - height, y + amount);
    }
}
