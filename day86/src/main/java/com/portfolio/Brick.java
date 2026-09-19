package com.portfolio;

public class Brick {

    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private boolean alive = true;

    public Brick(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isAlive() {
        return alive;
    }

    public void destroy() {
        alive = false;
    }

    public double getLeft() {
        return x;
    }

    public double getRight() {
        return x + width;
    }

    public double getTop() {
        return y;
    }

    public double getBottom() {
        return y + height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Simple axis-aligned overlap test between this brick and a ball's bounding box - close
     * enough for a Breakout clone's ball/brick collisions.
     */
    public boolean intersects(Ball ball) {
        return ball.getRight() >= getLeft() && ball.getLeft() <= getRight()
                && ball.getBottom() >= getTop() && ball.getTop() <= getBottom();
    }
}
