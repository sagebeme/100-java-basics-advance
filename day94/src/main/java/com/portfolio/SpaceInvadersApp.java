package com.portfolio;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SpaceInvadersApp extends Application {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 600;
    private static final double PLAYER_SPEED = 300; // pixels/second

    private final SpaceInvadersGame game = new SpaceInvadersGame(WIDTH, HEIGHT);
    private boolean movingLeft;
    private boolean movingRight;
    private boolean shooting;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = true;
            if (e.getCode() == KeyCode.RIGHT) movingRight = true;
            if (e.getCode() == KeyCode.SPACE) shooting = true;
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = false;
            if (e.getCode() == KeyCode.RIGHT) movingRight = false;
            if (e.getCode() == KeyCode.SPACE) shooting = false;
        });

        stage.setScene(scene);
        stage.setTitle("Space Invaders");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastNanos = -1;

            @Override
            public void handle(long now) {
                if (lastNanos < 0) {
                    lastNanos = now;
                    return;
                }
                double dt = (now - lastNanos) / 1_000_000_000.0;
                lastNanos = now;

                if (movingLeft) game.movePlayerTo(game.getPlayer().getX() - PLAYER_SPEED * dt);
                if (movingRight) game.movePlayerTo(game.getPlayer().getX() + PLAYER_SPEED * dt);
                if (shooting) game.shoot();
                game.update(dt);

                render(gc);

                if (game.isGameOver()) {
                    stop();
                }
            }
        };
        timer.start();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.LIMEGREEN);
        for (Enemy enemy : game.getEnemies()) {
            if (enemy.isAlive()) {
                gc.fillRect(enemy.getLeft(), enemy.getTop(), enemy.getWidth(), enemy.getHeight());
            }
        }

        gc.setFill(Color.YELLOW);
        for (Bullet bullet : game.getPlayerBullets()) {
            gc.fillRect(bullet.getLeft(), bullet.getTop(), SpaceInvadersGame.BULLET_WIDTH, SpaceInvadersGame.BULLET_HEIGHT);
        }

        gc.setFill(Color.DODGERBLUE);
        gc.fillRect(game.getPlayer().getX(), game.getPlayer().getY(), game.getPlayer().getWidth(), game.getPlayer().getHeight());

        gc.setFill(Color.WHITE);
        gc.setFont(new Font(16));
        gc.fillText("Score: " + game.getScore() + "   Wave: " + game.getWave(), 10, 20);

        if (game.isGameOver()) {
            gc.fillText("GAME OVER", WIDTH / 2 - 40, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
