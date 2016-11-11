
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NameSurfer extends Application implements NameSurferConstants {
    NameSurferDataBase database;
    NameSurferGraph graphPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpDatabase();
        setUpUi(primaryStage);
    }

    private void setUpDatabase() {
        database = new NameSurferDataBase(NAMES_DATA_FILE);
    }

    private void setUpUi(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        mainPane.setPrefSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        graphPane = new NameSurferGraph();
        mainPane.setCenter(graphPane);

        HBox bottomPane = createBottomPane();
        mainPane.setBottom(bottomPane);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createBottomPane() {
        HBox bottomPane = new HBox();
        bottomPane.setPadding(new Insets(15, 12, 15, 12));
        bottomPane.setSpacing(10);
        centerContents(bottomPane);

        addComponentsToBottomPane(bottomPane);

        return bottomPane;
    }

    private void centerContents(HBox hbox) {
        hbox.setAlignment(Pos.CENTER);
    }

    private void addComponentsToBottomPane(HBox bottomPane) {
        Text nameLabel = new Text("Name");

        TextField nameInput = new TextField();

        Button buttonGraph = new Button("Graph");
        buttonGraph.setPrefSize(100, 20);
        buttonGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                graphPane.addEntry(database.findEntry(nameInput.getText()));
            }
        });

        Button buttonClear = new Button("Clear");
        buttonClear.setPrefSize(100, 20);
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                graphPane.clear();
                nameInput.clear();
                nameInput.requestFocus();
            }
        });

        bottomPane.getChildren().addAll(nameLabel, nameInput, buttonGraph, buttonClear);
    }
}
