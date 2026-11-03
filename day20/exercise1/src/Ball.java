public class Ball {

    // TODO: private fields for the position, the velocity and the screen size

    public Ball(int x, int y, int dx, int dy, int width, int height) {
        // TODO: refuse a screen of 0 or less, or a ball that starts off it; then store everything
    }

    public void step() {
        // TODO: move one frame, bouncing off any edge it would pass
    }

    public void step(int frames) {
        // TODO
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

    public static void main(String[] args) {
        Ball ball = new Ball(10, 20, 7, 5, 100, 60);
        for (int frame = 1; frame <= 20; frame++) {
            ball.step();
            System.out.println("frame " + frame + ": (" + ball.getX() + ", " + ball.getY() + ")");
        }
    }
}
