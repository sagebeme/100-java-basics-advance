public class Player {
    private double x;
    private double y;
    private final double speed;
    private final double width;
    private final double height;

    public Player(double x, double y, double speed, double width, double height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void moveUp() { y = Math.max(0, y - speed); }
    public void moveDown() { y = Math.min(height, y + speed); }
    public void moveLeft() { x = Math.max(0, x - speed); }
    public void moveRight() { x = Math.min(width, x + speed); }
}
