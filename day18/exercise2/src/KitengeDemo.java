import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Draws three kitenge swatches side by side: a checkerboard, stripes and a spiral, using Pattern's rules.
public class KitengeDemo extends Application {

    private static final int CELL = 20;
    private static final int SQUARES = 9;
    private static final int SWATCH = CELL * SQUARES;
    private static final int GAP = 30;
    private static final String[] PALETTE = {"#b03a2e", "#d4a017", "#1e6f5c", "#2c3e50"};

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(GAP + 3 * (SWATCH + GAP), SWATCH + 2 * GAP);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.web("#f6efe1"));
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int[][] spiral = Pattern.spiral(SQUARES);
        for (int row = 0; row < SQUARES; row++) {
            for (int col = 0; col < SQUARES; col++) {
                double y = GAP + row * CELL;

                g.setFill(Pattern.isDark(row, col) ? Color.web("#2c3e50") : Color.web("#d4a017"));
                g.fillRect(GAP + col * CELL, y, CELL, CELL);

                g.setFill(Color.web(Pattern.stripe(row, PALETTE)));
                g.fillRect(2 * GAP + SWATCH + col * CELL, y, CELL, CELL);

                // The spiral fades from deep red at the edge to pale gold in the middle.
                double t = (spiral[row][col] - 1) / (double) (SQUARES * SQUARES - 1);
                g.setFill(Color.web("#b03a2e").interpolate(Color.web("#f3d27a"), t));
                g.fillRect(3 * GAP + 2 * SWATCH + col * CELL, y, CELL, CELL);
            }
        }

        stage.setTitle("Kitenge patterns");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
