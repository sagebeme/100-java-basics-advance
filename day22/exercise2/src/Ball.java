public class Ball {
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;

    public Ball(double x, double y, double velocityX, double velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVelocityX() { return velocityX; }
    public double getVelocityY() { return velocityY; }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public void bounceX() {
        velocityX = -velocityX;
    }

    public void bounceY() {
        velocityY = -velocityY;
    }

    public boolean collidesWith(Paddle paddle, double paddleX) {
        return x <= paddleX && y >= paddle.getY() && y <= paddle.getY() + paddle.getHeight();
    }
}
