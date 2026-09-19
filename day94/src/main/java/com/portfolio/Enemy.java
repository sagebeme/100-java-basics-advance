package com.portfolio;

public class Enemy {

    private double x;
    private double y;
    private final double width;
    private final double height;
    private boolean alive = true;

    public Enemy(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void moveBy(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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

    public boolean isAlive() {
        return alive;
    }

    public void destroy() {
        alive = false;
    }

    public boolean intersects(Bullet bullet) {
        return bullet.getRight() >= getLeft() && bullet.getLeft() <= getRight()
                && bullet.getBottom() >= getTop() && bullet.getTop() <= getBottom();
    }
}
