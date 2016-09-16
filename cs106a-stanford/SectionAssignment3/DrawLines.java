import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawLines extends Application {
    private static final int CANVAS_HEIGHT = 200;
    private static final int CANVAS_WIDTH = 300;

    private Line line;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(canvas);

        handleMousePress(root);
        handleMouseDrag(root);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // Handling the mouse press separately gives a more accurate starting point
    // for the line
    private void handleMousePress(final Group root) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                line = new Line(event.getSceneX(), event.getSceneY(), event.getSceneX(), event.getSceneY());
                root.getChildren().add(line);

            }
        });
    }

    private void handleMouseDrag(Group root) {
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                line.setEndX(event.getSceneX());
                line.setEndY(event.getSceneY());
            }
        });
    }
}
