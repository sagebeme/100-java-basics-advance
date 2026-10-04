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

public class BreakoutApp extends Application {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 600;
    private static final double PADDLE_SPEED = 350; // pixels/second

    private final BreakoutGame game = new BreakoutGame(WIDTH, HEIGHT);
    private boolean movingLeft;
    private boolean movingRight;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = true;
            if (e.getCode() == KeyCode.RIGHT) movingRight = true;
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = false;
            if (e.getCode() == KeyCode.RIGHT) movingRight = false;
        });

        stage.setScene(scene);
        stage.setTitle("Breakout");
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

                if (movingLeft) game.movePaddleTo(game.getPaddle().getX() - PADDLE_SPEED * dt);
                if (movingRight) game.movePaddleTo(game.getPaddle().getX() + PADDLE_SPEED * dt);
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
        gc.setFill(new javafx.scene.paint.LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new javafx.scene.paint.Stop(0, Color.web("#1a1033")), new javafx.scene.paint.Stop(1, Color.web("#0b0618"))));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        Color[] rows = {Color.web("#ef476f"), Color.web("#f78c6b"), Color.web("#ffd166"), Color.web("#06d6a0"), Color.web("#118ab2"), Color.web("#9b5de5")};

        // each row of bricks gets its own kanga colour
        for (Brick brick : game.getBricks()) {
            if (brick.isAlive()) {
                gc.setFill(rows[(int) (brick.getTop() / Math.max(1, brick.getHeight() + 4)) % rows.length]);
                gc.fillRoundRect(brick.getLeft() + 1, brick.getTop() + 1, brick.getWidth() - 2, brick.getHeight() - 2, 6, 6);
                gc.setFill(Color.web("#ffffff", 0.25));
                gc.fillRect(brick.getLeft() + 3, brick.getTop() + 2, brick.getWidth() - 6, 2);
            }
        }

        gc.setFill(Color.web("#e0e1dd"));
        gc.fillRoundRect(game.getPaddle().getX(), game.getPaddle().getY(), game.getPaddle().getWidth(), game.getPaddle().getHeight(), 12, 12);

        double r = game.getBall().getRadius();
        gc.setFill(Color.web("#ffd166", 0.25));
        gc.fillOval(game.getBall().getLeft() - r, game.getBall().getTop() - r, r * 4, r * 4);
        gc.setFill(Color.web("#ffd166"));
        gc.fillOval(game.getBall().getLeft(), game.getBall().getTop(), r * 2, r * 2);
        gc.setFill(Color.web("#e0e1dd"));

        gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 15));
        gc.fillText("Score: " + game.getScore() + "   Lives: " + game.getLives() + "   Level: " + game.getLevel(), 10, 20);

        if (game.isGameOver()) {
            gc.fillText("GAME OVER", WIDTH / 2 - 40, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
