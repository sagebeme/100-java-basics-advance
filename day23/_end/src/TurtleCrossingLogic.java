public class TurtleCrossingLogic {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final double PLAYER_SIZE = 30;
    public static final double CAR_WIDTH = 50;
    public static final double CAR_HEIGHT = 30;

    public static double wrapCarX(double x) {
        if (x > WIDTH) return -50;
        if (x < -50) return WIDTH;
        return x;
    }

    public static boolean collides(double playerX, double playerY, double carX, double carY) {
        return playerX < carX + CAR_WIDTH && playerX + PLAYER_SIZE > carX
                && playerY < carY + CAR_HEIGHT && playerY + PLAYER_SIZE > carY;
    }

    public static boolean hasReachedTop(double playerY) {
        return playerY < 50;
    }
}
