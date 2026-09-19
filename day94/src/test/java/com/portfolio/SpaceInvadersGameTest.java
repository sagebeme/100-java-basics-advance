package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceInvadersGameTest {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 600;

    @Test
    void startsWithAFullGridOfAliveEnemiesAndZeroScore() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);

        assertEquals(24, game.getEnemies().size()); // 4 rows x 6 cols
        assertTrue(game.getEnemies().stream().allMatch(Enemy::isAlive));
        assertEquals(0, game.getScore());
        assertEquals(1, game.getWave());
        assertFalse(game.isGameOver());
    }

    @Test
    void shootingAddsABulletAtThePlayersPosition() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);

        boolean fired = game.shoot();

        assertTrue(fired);
        assertEquals(1, game.getPlayerBullets().size());
        assertEquals(game.getPlayer().getCenterX(), game.getPlayerBullets().get(0).getX() + SpaceInvadersGame.BULLET_WIDTH / 2, 0.01);
    }

    @Test
    void shootingRespectsTheCooldownBeforeAllowingAnotherShot() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);

        assertTrue(game.shoot());
        assertFalse(game.shoot()); // still on cooldown
        assertEquals(1, game.getPlayerBullets().size());

        game.update(SpaceInvadersGame.SHOOT_COOLDOWN_SECONDS + 0.01);
        assertTrue(game.shoot());
        assertEquals(2, game.getPlayerBullets().size());
    }

    @Test
    void bulletsThatLeaveTheTopOfTheScreenAreRemoved() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        game.shoot();

        // Advance far enough that the bullet's speed carries it well past the top edge.
        game.update(5.0);

        assertTrue(game.getPlayerBullets().isEmpty());
    }

    @Test
    void aBulletHittingAnEnemyDestroysItAndAddsScore() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        Enemy target = game.getEnemies().get(0);
        double centerX = (target.getLeft() + target.getRight()) / 2;
        double centerY = (target.getTop() + target.getBottom()) / 2;

        game.shoot();
        game.getPlayerBullets().get(0).setPosition(centerX, centerY);

        game.update(0.001);

        assertFalse(target.isAlive());
        assertEquals(SpaceInvadersGame.POINTS_PER_ENEMY, game.getScore());
        assertTrue(game.getPlayerBullets().isEmpty()); // the bullet is consumed on impact
    }

    @Test
    void allEnemiesDestroyedAdvancesToANewWaveWithAFreshFullGrid() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        game.getEnemies().forEach(Enemy::destroy);

        game.update(0.001);

        assertEquals(2, game.getWave());
        assertEquals(24, game.getEnemies().size());
        assertTrue(game.getEnemies().stream().allMatch(Enemy::isAlive));
    }

    @Test
    void enemiesReachingThePlayerRowEndsTheGame() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        Enemy enemy = game.getEnemies().get(0);
        enemy.moveBy(0, game.getPlayer().getTop() - enemy.getBottom()); // push it down to the player's row

        game.update(0.001);

        assertTrue(game.isGameOver());
    }

    @Test
    void updateDoesNothingOnceTheGameIsOver() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        Enemy enemy = game.getEnemies().get(0);
        enemy.moveBy(0, game.getPlayer().getTop() - enemy.getBottom());
        game.update(0.001);
        assertTrue(game.isGameOver());

        int scoreBefore = game.getScore();
        game.shoot(); // shoot() itself checks gameOver and refuses
        game.update(1.0);

        assertEquals(scoreBefore, game.getScore());
        assertTrue(game.getPlayerBullets().isEmpty());
    }

    @Test
    void movingThePlayerClampsToTheBoardBounds() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);

        game.movePlayerTo(-100);
        assertEquals(0, game.getPlayer().getX(), 0.001);

        game.movePlayerTo(WIDTH + 100);
        assertEquals(WIDTH - game.getPlayer().getWidth(), game.getPlayer().getX(), 0.001);
    }

    @Test
    void enemiesReverseDirectionAndDescendWhenTheyReachTheBoardEdge() {
        SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
        Enemy rightmost = game.getEnemies().stream().max((a, b) -> Double.compare(a.getRight(), b.getRight())).orElseThrow();
        double originalTop = rightmost.getTop();

        // Push the rightmost enemy to the very edge so the next update must trigger a bounce.
        rightmost.moveBy(WIDTH - rightmost.getRight(), 0);

        game.update(0.1);

        assertEquals(originalTop + 20, rightmost.getTop(), 0.01); // descended by ENEMY_DESCEND_STEP
    }
}
