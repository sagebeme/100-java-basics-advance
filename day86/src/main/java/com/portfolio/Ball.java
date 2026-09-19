package com.portfolio;

public class Ball {

    private double x;
    private double y;
    private double vx;
    private double vy;
    private final double radius;

    public Ball(double x, double y, double vx, double vy, double radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void advance(double dtSeconds) {
        x += vx * dtSeconds;
        y += vy * dtSeconds;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public double getRadius() {
        return radius;
    }

    public double getLeft() {
        return x - radius;
    }

    public double getRight() {
        return x + radius;
    }

    public double getTop() {
        return y - radius;
    }

    public double getBottom() {
        return y + radius;
    }
}
