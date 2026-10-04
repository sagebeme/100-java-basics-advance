import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

// Draws a little "shape garden" on a canvas: a square, a rectangle, circles, an oval, lines and polygons.
public class ShapesDemo extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(560, 360);
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.setFill(Color.web("#fdf6e3"));
        g.fillRect(0, 0, 560, 360);

        g.setFill(Color.web("#e76f51"));                 // a square
        g.fillRoundRect(40, 40, 110, 110, 18, 18);
        double[] corner = Shapes.centredRectangle(280, 95, 180, 70);
        g.setFill(Color.web("#2a9d8f"));                 // a rectangle, centred
        g.fillRect(corner[0], corner[1], 180, 70);

        g.setFill(Color.web("#f4a261"));                 // circles
        for (int i = 0; i < 4; i++) g.fillOval(420 + i * 22, 60 + i * 12, 60 - i * 10, 60 - i * 10);
        g.setStroke(Color.web("#264653"));               // an oval outline
        g.setLineWidth(4);
        g.strokeOval(40, 200, 170, 110);

        g.setLineCap(StrokeLineCap.ROUND);               // lines
        for (int i = 0; i < 6; i++) {
            g.setStroke(Color.hsb(i * 50, 0.7, 0.8));
            g.strokeLine(250 + i * 14, 210, 250 + i * 14, 320);
        }

        double[][] hex = Shapes.regularPolygon(450, 265, 60, 6);   // polygons
        g.setFill(Color.web("#264653"));
        g.fillPolygon(hex[0], hex[1], 6);
        double[][] tri = Shapes.regularPolygon(450, 270, 30, 3);
        g.setFill(Color.web("#e9c46a"));
        g.fillPolygon(tri[0], tri[1], 3);

        stage.setTitle("Shape garden");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
