public class Exercise2 {
    public static void main(String[] args) {
        Ball ball = new Ball(400, 300, 5, 3);
        ball.move();
        System.out.println("Ball at: (" + ball.getX() + ", " + ball.getY() + ")");
    }
}
