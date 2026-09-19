package com.portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BreakoutGame {

    public static final double BALL_RADIUS = 8;
    public static final double PADDLE_WIDTH = 80;
    public static final double PADDLE_HEIGHT = 10;
    public static final int POINTS_PER_BRICK = 10;
    public static final int STARTING_LIVES = 3;
    private static final double BASE_BALL_SPEED = 200; // pixels/second
    private static final int BRICK_COLS = 5;
    private static final double BRICK_HEIGHT = 20;
    private static final double BRICK_GAP = 5;
    private static final double BRICK_TOP_MARGIN = 40;

    private final double boardWidth;
    private final double boardHeight;

    private Ball ball;
    private final Paddle paddle;
    private List<Brick> bricks;
    private int score = 0;
    private int lives = STARTING_LIVES;
    private int level = 1;
    private boolean gameOver = false;

    public BreakoutGame(double boardWidth, double boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.paddle = new Paddle((boardWidth - PADDLE_WIDTH) / 2, boardHeight - 30, PADDLE_WIDTH, PADDLE_HEIGHT, boardWidth);
        this.bricks = buildBricksForLevel(level);
        resetBall();
    }

    private List<Brick> buildBricksForLevel(int level) {
        int rows = 3 + level; // more rows each level
        double brickWidth = (boardWidth - BRICK_GAP * (BRICK_COLS + 1)) / BRICK_COLS;
        List<Brick> result = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < BRICK_COLS; col++) {
                double x = BRICK_GAP + col * (brickWidth + BRICK_GAP);
                double y = BRICK_TOP_MARGIN + row * (BRICK_HEIGHT + BRICK_GAP);
                result.add(new Brick(x, y, brickWidth, BRICK_HEIGHT));
            }
        }
        return result;
    }

    private void resetBall() {
        double speed = BASE_BALL_SPEED * (1 + 0.1 * (level - 1));
        ball = new Ball(boardWidth / 2, paddle.getTop() - BALL_RADIUS - 1, speed * 0.6, -speed * 0.8, BALL_RADIUS);
    }

    public void movePaddleTo(double x) {
        paddle.moveTo(x);
    }

    public void update(double dtSeconds) {
        if (gameOver) {
            return;
        }
        ball.advance(dtSeconds);
        handleWallCollisions();
        handlePaddleCollision();
        handleBrickCollisions();
        handleBallOutOfBounds();
        checkLevelComplete();
    }

    private void handleWallCollisions() {
        if (ball.getLeft() <= 0) {
            ball.setPosition(ball.getRadius(), ball.getY());
            ball.setVelocity(-ball.getVx(), ball.getVy());
        } else if (ball.getRight() >= boardWidth) {
            ball.setPosition(boardWidth - ball.getRadius(), ball.getY());
            ball.setVelocity(-ball.getVx(), ball.getVy());
        }
        if (ball.getTop() <= 0) {
            ball.setPosition(ball.getX(), ball.getRadius());
            ball.setVelocity(ball.getVx(), -ball.getVy());
        }
    }

    private void handlePaddleCollision() {
        boolean movingDown = ball.getVy() > 0;
        boolean withinPaddleX = ball.getRight() >= paddle.getLeft() && ball.getLeft() <= paddle.getRight();
        boolean touchingPaddleTop = ball.getBottom() >= paddle.getTop() && ball.getTop() <= paddle.getBottom();
        if (movingDown && withinPaddleX && touchingPaddleTop) {
            ball.setPosition(ball.getX(), paddle.getTop() - ball.getRadius());
            ball.setVelocity(ball.getVx(), -Math.abs(ball.getVy()));
        }
    }

    private void handleBrickCollisions() {
        for (Brick brick : bricks) {
            if (brick.isAlive() && brick.intersects(ball)) {
                brick.destroy();
                score += POINTS_PER_BRICK;
                ball.setVelocity(ball.getVx(), -ball.getVy());
                return; // only resolve one brick per update to keep collision response simple
            }
        }
    }

    private void handleBallOutOfBounds() {
        if (ball.getTop() > boardHeight) {
            lives--;
            if (lives <= 0) {
                gameOver = true;
            } else {
                resetBall();
            }
        }
    }

    private void checkLevelComplete() {
        boolean allDestroyed = bricks.stream().noneMatch(Brick::isAlive);
        if (allDestroyed) {
            level++;
            bricks = buildBricksForLevel(level);
            resetBall();
        }
    }

    public double getBoardWidth() {
        return boardWidth;
    }

    public double getBoardHeight() {
        return boardHeight;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Brick> getBricks() {
        return Collections.unmodifiableList(bricks);
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
