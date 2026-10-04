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
        gc.setFill(Color.web("#05040f"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        java.util.Random stars = new java.util.Random(94);        // the same starfield every frame
        for (int i = 0; i < 90; i++) {
            gc.setFill(Color.web("#ffffff", 0.25 + stars.nextDouble() * 0.6));
            double size = stars.nextDouble() < 0.1 ? 2.5 : 1.2;
            gc.fillOval(stars.nextDouble() * WIDTH, stars.nextDouble() * HEIGHT, size, size);
        }
        Color[] waves = {Color.web("#f72585"), Color.web("#b5179e"), Color.web("#7209b7"), Color.web("#4361ee"), Color.web("#4cc9f0")};

        // invaders: a body, two legs and two eyes, coloured by row
        for (Enemy enemy : game.getEnemies()) {
            if (enemy.isAlive()) {
                double x = enemy.getLeft(), y = enemy.getTop(), w = enemy.getWidth(), h = enemy.getHeight();
                gc.setFill(waves[(int) (y / Math.max(1, h * 1.5)) % waves.length]);
                gc.fillRoundRect(x, y, w, h * 0.7, 8, 8);
                gc.fillRect(x + w * 0.1, y + h * 0.6, w * 0.15, h * 0.4);
                gc.fillRect(x + w * 0.75, y + h * 0.6, w * 0.15, h * 0.4);
                gc.setFill(Color.web("#05040f"));
                gc.fillRect(x + w * 0.25, y + h * 0.2, w * 0.15, h * 0.18);
                gc.fillRect(x + w * 0.6, y + h * 0.2, w * 0.15, h * 0.18);
            }
        }

        gc.setFill(Color.web("#ffd60a"));
        for (Bullet bullet : game.getPlayerBullets()) {
            gc.fillRect(bullet.getLeft(), bullet.getTop(), SpaceInvadersGame.BULLET_WIDTH, SpaceInvadersGame.BULLET_HEIGHT);
        }

        double px = game.getPlayer().getX(), py = game.getPlayer().getY(), pw = game.getPlayer().getWidth(), ph = game.getPlayer().getHeight();
        gc.setFill(Color.web("#4cc9f0"));                           // the ship: a body and a nose
        gc.fillRoundRect(px, py + ph * 0.35, pw, ph * 0.65, 8, 8);
        gc.fillPolygon(new double[]{px + pw * 0.35, px + pw / 2, px + pw * 0.65}, new double[]{py + ph * 0.4, py, py + ph * 0.4}, 3);

        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 15));
        gc.fillText("Score: " + game.getScore() + "   Wave: " + game.getWave(), 10, 20);

        if (game.isGameOver()) {
            gc.fillText("GAME OVER", WIDTH / 2 - 40, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
