package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreakoutGameTest {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 600;

    @Test
    void startsWithFullLivesZeroScoreAndLevelOne() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);

        assertEquals(0, game.getScore());
        assertEquals(BreakoutGame.STARTING_LIVES, game.getLives());
        assertEquals(1, game.getLevel());
        assertFalse(game.isGameOver());
        assertEquals(5 * 4, game.getBricks().size()); // 5 cols x (3 + level 1) rows
        assertTrue(game.getBricks().stream().allMatch(Brick::isAlive));
    }

    @Test
    void ballMovesAccordingToItsVelocityWhenNothingIsInTheWay() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        game.getBall().setPosition(200, 300);
        game.getBall().setVelocity(50, 60);

        game.update(0.1);

        assertEquals(205, game.getBall().getX(), 0.001);
        assertEquals(306, game.getBall().getY(), 0.001);
    }

    @Test
    void bouncesOffTheLeftWall() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        game.getBall().setPosition(3, 100);
        game.getBall().setVelocity(-50, 0);

        game.update(0.1); // would move to x = -2, past the left wall

        assertEquals(game.getBall().getRadius(), game.getBall().getX(), 0.001);
        assertEquals(50, game.getBall().getVx(), 0.001);
    }

    @Test
    void bouncesOffTheRightWall() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        game.getBall().setPosition(WIDTH - 3, 100);
        game.getBall().setVelocity(50, 0);

        game.update(0.1);

        assertEquals(WIDTH - game.getBall().getRadius(), game.getBall().getX(), 0.001);
        assertEquals(-50, game.getBall().getVx(), 0.001);
    }

    @Test
    void bouncesOffTheTopWall() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        game.getBall().setPosition(200, 3);
        game.getBall().setVelocity(0, -50);

        game.update(0.1);

        assertEquals(game.getBall().getRadius(), game.getBall().getY(), 0.001);
        assertEquals(50, game.getBall().getVy(), 0.001);
    }

    @Test
    void bouncesOffThePaddleWhenMovingDownIntoIt() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        Paddle paddle = game.getPaddle();
        game.getBall().setPosition(paddle.getCenterX(), paddle.getTop() - 3);
        game.getBall().setVelocity(0, 50);

        game.update(0.1); // would move the ball into the paddle

        assertTrue(game.getBall().getVy() < 0, "Ball should bounce upward off the paddle");
        assertEquals(paddle.getTop() - game.getBall().getRadius(), game.getBall().getY(), 0.001);
    }

    @Test
    void doesNotBounceOffThePaddleWhenMovingAwayFromIt() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        Paddle paddle = game.getPaddle();
        game.getBall().setPosition(paddle.getCenterX(), paddle.getTop() - 20);
        game.getBall().setVelocity(0, -50); // already moving up, away from paddle

        game.update(0.1);

        assertEquals(-50, game.getBall().getVy(), 0.001);
    }

    @Test
    void breakingABrickAddsScoreAndDestroysOnlyThatBrick() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        Brick target = game.getBricks().get(0);
        double targetCenterX = (target.getLeft() + target.getRight()) / 2;
        double targetCenterY = (target.getTop() + target.getBottom()) / 2;

        game.getBall().setPosition(targetCenterX, targetCenterY);
        game.getBall().setVelocity(0, 10);

        game.update(0.001); // tiny step, ball is already overlapping the brick

        assertFalse(target.isAlive());
        assertEquals(BreakoutGame.POINTS_PER_BRICK, game.getScore());
        long stillAlive = game.getBricks().stream().filter(Brick::isAlive).count();
        assertEquals(game.getBricks().size() - 1, stillAlive);
    }

    @Test
    void losingAllLivesEndsTheGame() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);

        for (int i = 0; i < BreakoutGame.STARTING_LIVES; i++) {
            assertFalse(game.isGameOver());
            game.getBall().setPosition(200, HEIGHT + 50); // past the bottom edge
            game.getBall().setVelocity(0, 10);
            game.update(0.001);
        }

        assertTrue(game.isGameOver());
        assertEquals(0, game.getLives());
    }

    @Test
    void updateDoesNothingOnceTheGameIsOver() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        for (int i = 0; i < BreakoutGame.STARTING_LIVES; i++) {
            game.getBall().setPosition(200, HEIGHT + 50);
            game.getBall().setVelocity(0, 10);
            game.update(0.001);
        }
        assertTrue(game.isGameOver());

        double xBefore = game.getBall().getX();
        int scoreBefore = game.getScore();
        game.update(1.0);

        assertEquals(xBefore, game.getBall().getX(), 0.001);
        assertEquals(scoreBefore, game.getScore());
    }

    @Test
    void destroyingEveryBrickAdvancesToTheNextLevelWithAFreshFullBoard() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
        game.getBricks().forEach(Brick::destroy);

        game.update(0.001);

        assertEquals(2, game.getLevel());
        assertFalse(game.getBricks().isEmpty());
        assertTrue(game.getBricks().stream().allMatch(Brick::isAlive));
        assertEquals(5 * 5, game.getBricks().size()); // 5 cols x (3 + level 2) rows
    }

    @Test
    void movingThePaddleClampsToTheBoardBounds() {
        BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);

        game.movePaddleTo(-100);
        assertEquals(0, game.getPaddle().getX(), 0.001);

        game.movePaddleTo(WIDTH + 100);
        assertEquals(WIDTH - game.getPaddle().getWidth(), game.getPaddle().getX(), 0.001);
    }
}
