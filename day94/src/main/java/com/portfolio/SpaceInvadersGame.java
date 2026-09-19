package com.portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpaceInvadersGame {

    public static final double PLAYER_WIDTH = 40;
    public static final double PLAYER_HEIGHT = 20;
    public static final double BULLET_WIDTH = 4;
    public static final double BULLET_HEIGHT = 10;
    public static final double BULLET_SPEED = 300; // pixels/second, upward
    public static final double SHOOT_COOLDOWN_SECONDS = 0.3;
    public static final int POINTS_PER_ENEMY = 10;
    private static final int ENEMY_ROWS = 4;
    private static final int ENEMY_COLS = 6;
    private static final double ENEMY_WIDTH = 30;
    private static final double ENEMY_HEIGHT = 20;
    private static final double ENEMY_GAP = 10;
    private static final double ENEMY_TOP_MARGIN = 40;
    private static final double ENEMY_DESCEND_STEP = 20;
    private static final double BASE_ENEMY_SPEED = 40; // pixels/second

    private final double boardWidth;
    private final double boardHeight;

    private final Player player;
    private final List<Bullet> playerBullets = new ArrayList<>();
    private List<Enemy> enemies;
    private int enemyDirection = 1;
    private double enemySpeed;
    private double shootCooldownRemaining = 0;

    private int score = 0;
    private int wave = 1;
    private boolean gameOver = false;

    public SpaceInvadersGame(double boardWidth, double boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.player = new Player((boardWidth - PLAYER_WIDTH) / 2, boardHeight - 30, PLAYER_WIDTH, PLAYER_HEIGHT, boardWidth);
        this.enemies = buildWave(wave);
        this.enemySpeed = BASE_ENEMY_SPEED * (1 + 0.2 * (wave - 1));
    }

    private List<Enemy> buildWave(int wave) {
        List<Enemy> result = new ArrayList<>();
        for (int row = 0; row < ENEMY_ROWS; row++) {
            for (int col = 0; col < ENEMY_COLS; col++) {
                double x = ENEMY_GAP + col * (ENEMY_WIDTH + ENEMY_GAP);
                double y = ENEMY_TOP_MARGIN + row * (ENEMY_HEIGHT + ENEMY_GAP);
                result.add(new Enemy(x, y, ENEMY_WIDTH, ENEMY_HEIGHT));
            }
        }
        return result;
    }

    public void movePlayerTo(double x) {
        player.moveTo(x);
    }

    public boolean shoot() {
        if (gameOver || shootCooldownRemaining > 0) {
            return false;
        }
        playerBullets.add(new Bullet(player.getCenterX() - BULLET_WIDTH / 2, player.getTop() - BULLET_HEIGHT,
                -BULLET_SPEED, BULLET_WIDTH, BULLET_HEIGHT));
        shootCooldownRemaining = SHOOT_COOLDOWN_SECONDS;
        return true;
    }

    public void update(double dtSeconds) {
        if (gameOver) {
            return;
        }
        shootCooldownRemaining = Math.max(0, shootCooldownRemaining - dtSeconds);

        for (Bullet bullet : playerBullets) {
            bullet.advance(dtSeconds);
        }
        playerBullets.removeIf(b -> b.getBottom() < 0);

        moveEnemies(dtSeconds);
        handleBulletEnemyCollisions();
        checkEnemiesReachedPlayer();
        checkWaveComplete();
    }

    private void moveEnemies(double dtSeconds) {
        double step = enemyDirection * enemySpeed * dtSeconds;
        boolean wouldExceedBounds = enemies.stream()
                .filter(Enemy::isAlive)
                .anyMatch(e -> e.getLeft() + step < 0 || e.getRight() + step > boardWidth);

        if (wouldExceedBounds) {
            enemyDirection = -enemyDirection;
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.moveBy(0, ENEMY_DESCEND_STEP);
                }
            }
        } else {
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.moveBy(step, 0);
                }
            }
        }
    }

    private void handleBulletEnemyCollisions() {
        for (Bullet bullet : playerBullets) {
            if (!bullet.isAlive()) {
                continue;
            }
            for (Enemy enemy : enemies) {
                if (enemy.isAlive() && enemy.intersects(bullet)) {
                    enemy.destroy();
                    bullet.destroy();
                    score += POINTS_PER_ENEMY;
                    break;
                }
            }
        }
        playerBullets.removeIf(b -> !b.isAlive());
    }

    private void checkEnemiesReachedPlayer() {
        boolean reachedPlayer = enemies.stream()
                .filter(Enemy::isAlive)
                .anyMatch(e -> e.getBottom() >= player.getTop());
        if (reachedPlayer) {
            gameOver = true;
        }
    }

    private void checkWaveComplete() {
        boolean allDestroyed = enemies.stream().noneMatch(Enemy::isAlive);
        if (allDestroyed) {
            wave++;
            enemies = buildWave(wave);
            enemyDirection = 1;
            enemySpeed = BASE_ENEMY_SPEED * (1 + 0.2 * (wave - 1));
            playerBullets.clear();
        }
    }

    public double getBoardWidth() {
        return boardWidth;
    }

    public double getBoardHeight() {
        return boardHeight;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Bullet> getPlayerBullets() {
        return Collections.unmodifiableList(playerBullets);
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public int getScore() {
        return score;
    }

    public int getWave() {
        return wave;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
