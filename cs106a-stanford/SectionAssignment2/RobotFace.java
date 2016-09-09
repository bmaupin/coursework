import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RobotFace extends Application {
    private static final int CANVAS_HEIGHT = 200;
    private static final int CANVAS_WIDTH = 300;

    private static final int EYE_RADIUS = 10;

    private static final int HEAD_HEIGHT = 150;
    private static final int HEAD_WIDTH = 100;

    private static final int MOUTH_HEIGHT = 20;
    private static final int MOUTH_WIDTH = 60;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawRobotFace(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void drawRobotFace(GraphicsContext gc) {
        drawRobotHead(gc);
        drawRobotEyes(gc);
        drawRobotMouth(gc);
    }

    private static void drawRobotHead(GraphicsContext gc) {
        /*
         * The head is a big rectangle whose dimensions are given by the named
         * constants HEAD_WIDTH and HEAD_HEIGHT. The interior of the head is
         * gray, although it should be framed in black.
         */

        double x = (CANVAS_WIDTH - HEAD_WIDTH) / 2;
        double y = (CANVAS_HEIGHT - HEAD_HEIGHT) / 2;

        gc.setFill(Color.GREY);
        gc.fillRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
    }

    private static void drawRobotEyes(GraphicsContext gc) {
        /*
         * The eyes should be cricles whose radius in pixels is given by the
         * named constant EYE_RADIUS. The centers of the eyes should be set
         * horizontally a quarter of the width of the head in from either edge,
         */

        gc.setFill(Color.YELLOW);
        double eyeHeight = EYE_RADIUS * 2;
        double eyeWidth = eyeHeight;

        // Draw first eye
        double x = (CANVAS_WIDTH - HEAD_WIDTH) / 2 + HEAD_WIDTH / 4 - EYE_RADIUS;
        double y = (CANVAS_HEIGHT - HEAD_HEIGHT) / 2 + HEAD_HEIGHT / 4 - EYE_RADIUS;
        gc.fillOval(x, y, eyeWidth, eyeHeight);

        // Draw second eye
        x = (CANVAS_WIDTH - HEAD_WIDTH) / 2 + HEAD_WIDTH * 3 / 4 - EYE_RADIUS;
        gc.fillOval(x, y, eyeWidth, eyeHeight);
    }

    private static void drawRobotMouth(GraphicsContext gc) {
        /*
         * The mouth should be centered with respect to the head in the
         * x-dimension and one quarter of the distance up from the bottom of the
         * head in the y-dimension. The dimensions of the mouth are given by the
         * named constants MOUTH_WIDTH and MOUTH_HEIGHT. The mouth is white.
         */

        gc.setFill(Color.WHITE);

        double x = (CANVAS_WIDTH - HEAD_WIDTH) / 2 + (HEAD_WIDTH - MOUTH_WIDTH) / 2;
        double y = (CANVAS_HEIGHT - HEAD_HEIGHT) / 2 + HEAD_HEIGHT * 3 / 4 - MOUTH_HEIGHT / 2;

        gc.fillRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
    }
}