import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FlipHorizontal extends Application {
    private static final String IMAGE_FILENAME = ("estimation.png");

    private Image image;
    private ImageView imageView;
    private Group rootGroup;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setup();
        addImage();
        flipImageHorizontally();
        showImage(primaryStage);
    }

    private void setup() {
        rootGroup = new Group();
    }

    private void addImage() {
        image = new Image(IMAGE_FILENAME);
        imageView = new ImageView();
        imageView.setImage(image);
        rootGroup.getChildren().add(imageView);
    }

    private void flipImageHorizontally() {
        imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }

    private void showImage(Stage primaryStage) {
        setCanvasToImageSize();

        primaryStage.setScene(new Scene(rootGroup));
        primaryStage.show();
    }

    private void setCanvasToImageSize() {
        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        rootGroup.getChildren().add(canvas);
    }
}