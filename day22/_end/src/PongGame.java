import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PongGame extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private double paddle1Y = HEIGHT / 2;
    private double paddle2Y = HEIGHT / 2;
    private double ballX = WIDTH / 2;
    private double ballY = HEIGHT / 2;
    private double ballSpeedX = 5;
    private double ballSpeedY = 3;
    private int score1 = 0;
    private int score2 = 0;
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W: if (paddle1Y > 0) paddle1Y -= 20; break;
                case S: if (paddle1Y < HEIGHT - 100) paddle1Y += 20; break;
                case UP: if (paddle2Y > 0) paddle2Y -= 20; break;
                case DOWN: if (paddle2Y < HEIGHT - 100) paddle2Y += 20; break;
            }
        });
        
        new Thread(() -> {
            while (true) {
                update();
                javafx.application.Platform.runLater(() -> draw(gc));
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Pong Game");
        stage.setScene(scene);
        stage.show();
    }
    
    private void update() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        
        // Ball collision with paddles
        if (ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + 100) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballX >= WIDTH - 40 && ballY >= paddle2Y && ballY <= paddle2Y + 100) {
            ballSpeedX = -ballSpeedX;
        }
        
        // Ball collision with walls
        if (ballY <= 0 || ballY >= HEIGHT) {
            ballSpeedY = -ballSpeedY;
        }
        
        // Scoring
        if (ballX < 0) {
            score2++;
            resetBall();
        }
        if (ballX > WIDTH) {
            score1++;
            resetBall();
        }
    }
    
    private void resetBall() {
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        ballSpeedX = -ballSpeedX;
    }
    
    private void draw(GraphicsContext gc) {
        // A night court
        gc.setFill(Color.web("#0d1b2a"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.web("#e0e1dd", 0.25));
        gc.setLineWidth(4);
        gc.setLineDashes(18, 16);
        gc.strokeLine(WIDTH / 2.0, 0, WIDTH / 2.0, HEIGHT);
        gc.setLineDashes();
        gc.strokeOval(WIDTH / 2.0 - 60, HEIGHT / 2.0 - 60, 120, 120);

        // Big scores, behind the play
        gc.setFill(Color.web("#e0e1dd", 0.18));
        gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 96));
        gc.fillText(String.valueOf(score1), WIDTH / 2.0 - 130, 120);
        gc.fillText(String.valueOf(score2), WIDTH / 2.0 + 70, 120);
        gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 14));
        gc.setFill(Color.web("#e0e1dd", 0.7));
        gc.fillText("Player 1: " + score1, 50, 40);
        gc.fillText("Player 2: " + score2, WIDTH - 150, 40);

        // Paddles in two team colours, and a glowing ball
        gc.setFill(Color.web("#ff4d6d"));
        gc.fillRoundRect(10, paddle1Y, 10, 100, 10, 10);
        gc.setFill(Color.web("#4cc9f0"));
        gc.fillRoundRect(WIDTH - 20, paddle2Y, 10, 100, 10, 10);
        gc.setFill(Color.web("#f9c74f", 0.25));
        gc.fillOval(ballX - 6, ballY - 6, 32, 32);
        gc.setFill(Color.web("#f9c74f"));
        gc.fillOval(ballX, ballY, 20, 20);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

