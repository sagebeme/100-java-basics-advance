public class Exercise1 {
    public static void main(String[] args) {
        Paddle paddle = new Paddle(250, 100, 0, 600);
        paddle.moveUp(20);
        System.out.println("Paddle Y: " + paddle.getY());
    }
}
