package com.portfolio;

public class Paddle {

    private double x;
    private final double y;
    private final double width;
    private final double height;
    private final double boardWidth;

    public Paddle(double x, double y, double width, double height, double boardWidth) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.boardWidth = boardWidth;
        moveTo(x);
    }

    public void moveTo(double newX) {
        this.x = Math.max(0, Math.min(newX, boardWidth - width));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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

    public double getCenterX() {
        return x + width / 2;
    }
}
