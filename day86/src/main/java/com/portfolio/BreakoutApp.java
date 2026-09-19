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
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.ORANGE);
        for (Brick brick : game.getBricks()) {
            if (brick.isAlive()) {
                gc.fillRect(brick.getLeft(), brick.getTop(), brick.getWidth(), brick.getHeight());
            }
        }

        gc.setFill(Color.DODGERBLUE);
        gc.fillRect(game.getPaddle().getX(), game.getPaddle().getY(), game.getPaddle().getWidth(), game.getPaddle().getHeight());

        gc.setFill(Color.WHITE);
        gc.fillOval(game.getBall().getLeft(), game.getBall().getTop(), game.getBall().getRadius() * 2, game.getBall().getRadius() * 2);

        gc.setFont(new Font(16));
        gc.fillText("Score: " + game.getScore() + "   Lives: " + game.getLives() + "   Level: " + game.getLevel(), 10, 20);

        if (game.isGameOver()) {
            gc.fillText("GAME OVER", WIDTH / 2 - 40, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
