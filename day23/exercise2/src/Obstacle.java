public class Obstacle {
    private double x;
    private final double y;
    private final double speed;
    private final String type;

    public Obstacle(double x, double y, double speed, String type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public String getType() { return type; }

    public void move() {
        x += speed;
    }

    public boolean isOffScreen(double screenWidth) {
        return x < -50 || x > screenWidth + 50;
    }
}
