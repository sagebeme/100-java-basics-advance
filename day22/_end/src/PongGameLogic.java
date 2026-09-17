public class PongGameLogic {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDLE_HEIGHT = 100;
    public static final int BALL_SIZE = 20;

    public static boolean hitsLeftPaddle(double ballX, double ballY, double paddle1Y) {
        return ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT;
    }

    public static boolean hitsRightPaddle(double ballX, double ballY, double paddle2Y) {
        return ballX >= WIDTH - 40 && ballY >= paddle2Y && ballY <= paddle2Y + PADDLE_HEIGHT;
    }

    public static boolean hitsTopOrBottomWall(double ballY) {
        return ballY <= 0 || ballY >= HEIGHT;
    }

    public static boolean leftPlayerScores(double ballX) {
        return ballX > WIDTH;
    }

    public static boolean rightPlayerScores(double ballX) {
        return ballX < 0;
    }
}
