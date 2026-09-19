package com.portfolio;

public class Bullet {

    private double x;
    private double y;
    private final double vy;
    private final double width;
    private final double height;
    private boolean alive = true;

    public Bullet(double x, double y, double vy, double width, double height) {
        this.x = x;
        this.y = y;
        this.vy = vy;
        this.width = width;
        this.height = height;
    }

    public void advance(double dtSeconds) {
        y += vy * dtSeconds;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
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

    public boolean isAlive() {
        return alive;
    }

    public void destroy() {
        alive = false;
    }
}
