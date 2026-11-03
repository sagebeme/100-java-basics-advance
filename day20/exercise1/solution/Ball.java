public class Ball {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int width;
    private final int height;

    public Ball(int x, int y, int dx, int dy, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The screen must be bigger than 0");
        }
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new IllegalArgumentException("The ball starts off the screen at (" + x + ", " + y + ")");
        }
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }

    public void step() {
        x += dx;
        if (x > width) {
            x = 2 * width - x;
            dx = -dx;
        } else if (x < 0) {
            x = -x;
            dx = -dx;
        }

        y += dy;
        if (y > height) {
            y = 2 * height - y;
            dy = -dy;
        } else if (y < 0) {
            y = -y;
            dy = -dy;
        }
    }

    public void step(int frames) {
        for (int i = 0; i < frames; i++) {
            step();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static void main(String[] args) {
        Ball ball = new Ball(10, 20, 7, 5, 100, 60);
        for (int frame = 1; frame <= 20; frame++) {
            ball.step();
            System.out.println("frame " + frame + ": (" + ball.getX() + ", " + ball.getY() + ")");
        }
    }
}
