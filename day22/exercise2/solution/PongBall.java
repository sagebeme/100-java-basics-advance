public class PongBall {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int width;
    private final int height;
    private final int serveSpeed;

    public PongBall(int x, int y, int dx, int dy, int width, int height) {
        if (dx == 0) {
            throw new IllegalArgumentException("A ball that never goes sideways never reaches a paddle");
        }
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.serveSpeed = Math.abs(dx);
    }

    // One frame. Returns "LEFT" or "RIGHT" when that player scores, otherwise "".
    public String step(Paddle left, Paddle right) {
        x += dx;
        y += dy;
        if (y > height) {
            y = 2 * height - y;
            dy = -dy;
        } else if (y < 0) {
            y = -y;
            dy = -dy;
        }

        if (dx < 0 && x <= left.getX()) {
            if (!left.covers(y)) {
                serve(-1);
                return "RIGHT";
            }
            x = 2 * left.getX() - x;
            dx = -dx + 1;
        } else if (dx > 0 && x >= right.getX()) {
            if (!right.covers(y)) {
                serve(1);
                return "LEFT";
            }
            x = 2 * right.getX() - x;
            dx = -dx - 1;
        }
        return "";
    }

    // Back to the middle at the starting speed, heading towards the player who just lost the point.
    private void serve(int direction) {
        x = width / 2;
        y = height / 2;
        dx = direction * serveSpeed;
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
}
