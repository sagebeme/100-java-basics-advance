public class PongBall {

    // TODO: private fields: position, velocity, the court's size, and the serving speed

    public PongBall(int x, int y, int dx, int dy, int width, int height) {
        // TODO: refuse dx == 0; remember the starting speed for serves
    }

    // One frame. Returns "LEFT" or "RIGHT" when that player scores, otherwise "".
    public String step(Paddle left, Paddle right) {
        // TODO: move; bounce off the top and bottom; then check the paddles
        return "";
    }

    public int getX() {
        // TODO
        return -1;
    }

    public int getY() {
        // TODO
        return -1;
    }

    public int getDx() {
        // TODO
        return 0;
    }

    public int getDy() {
        // TODO
        return 0;
    }
}
