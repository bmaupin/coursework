import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RandomCircles extends Application {
    private static final int CANVAS_HEIGHT = 200;
    private static final int CANVAS_WIDTH = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < 10; i++) {
            drawRandomCircle(gc);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawRandomCircle(GraphicsContext gc) {
        Random random = new Random();

        // Generate a random radius between 5 and 50 (inclusive)
        int radius = random.nextInt(46) + 5;

        int x = random.nextInt(CANVAS_WIDTH - radius * 2);
        int y = random.nextInt(CANVAS_HEIGHT - radius * 2);

        Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

        gc.setFill(color);
        gc.fillOval(x, y, radius * 2, radius * 2);
    }
}
