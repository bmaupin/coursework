import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

public class FacePamphlet extends Application
        implements FacePamphletConstants {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpUi(primaryStage);
    }

    private void setUpUi(Stage primaryStage) {
        BorderPane parentPane = new BorderPane();
        parentPane.setPrefSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        HBox topPane = createTopPane();
        parentPane.setTop(topPane);

        VBox leftPane = createLeftPane();
        parentPane.setLeft(leftPane);

        Pane mainPane = createCenterPane();
        parentPane.setCenter(mainPane);

        Scene scene = new Scene(parentPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createTopPane() {
        HBox topPane = new HBox();

        styleTopPane(topPane);
        addComponentsToTopPane(topPane);

        return topPane;
    }

    private void styleTopPane(HBox topPane) {
        topPane.setPadding(new Insets(15, 12, 15, 12));
        topPane.setSpacing(10);
        topPane.setAlignment(Pos.CENTER);
    }

    private void addComponentsToTopPane(HBox topPane) {
        Text nameLabel = new Text("Name");

        TextField nameInput = new TextField();

        Button buttonAdd = new Button("Add");
        buttonAdd.setPrefSize(100, 20);
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        Button buttonDelete = new Button("Delete");
        buttonDelete.setPrefSize(100, 20);
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        Button buttonLookup = new Button("Lookup");
        buttonLookup.setPrefSize(100, 20);
        buttonLookup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        topPane.getChildren().addAll(nameLabel, nameInput, buttonAdd, buttonDelete, buttonLookup);
    }

    private VBox createLeftPane() {
        VBox leftPane = new VBox();

        styleLeftPane(leftPane);
        addComponentsToLeftPane(leftPane);

        return leftPane;
    }

    private void styleLeftPane(VBox leftPane) {
        leftPane.setPadding(new Insets(15, 12, 15, 12));
        leftPane.setSpacing(10);
        leftPane.setPrefHeight(APPLICATION_HEIGHT);
        leftPane.setAlignment(Pos.CENTER);
    }

    private void addComponentsToLeftPane(VBox leftPane) {
        TextField inputChangeStatus = new TextField();
        inputChangeStatus.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    // TODO
                }
            }
        });

        Button buttonChangeStatus = new Button("Change Status");
        setHeightWidthToFit(buttonChangeStatus);
        buttonChangeStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });
        VBox.setMargin(buttonChangeStatus, new Insets(0, 0, 20, 0));

        TextField inputChangePicture = new TextField();
        inputChangePicture.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    // TODO
                }
            }
        });

        Button buttonChangePicture = new Button("Change Picture");
        setHeightWidthToFit(buttonChangePicture);
        buttonChangePicture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });
        VBox.setMargin(buttonChangePicture, new Insets(0, 0, 20, 0));

        TextField inputAddFriend = new TextField();
        inputAddFriend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    // TODO
                }
            }
        });

        Button buttonAddFriend = new Button("Add Friend");
        setHeightWidthToFit(buttonAddFriend);
        buttonAddFriend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        leftPane.getChildren().addAll(inputChangeStatus, buttonChangeStatus, inputChangePicture, buttonChangePicture,
                inputAddFriend, buttonAddFriend);
    }

    private void setHeightWidthToFit(Region region) {
        region.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    private Pane createCenterPane() {
        Pane centerPane = new Pane();
        setWhiteBackground(centerPane);

        return centerPane;
    }

    private void setWhiteBackground(Pane pane) {
        pane.setStyle("-fx-background-color: white");
    }

    // /**
    // * This method has the responsibility for initializing the interactors in
    // * the application, and taking care of any other initialization that needs
    // * to be performed.
    // */
    // @Override
    // public void init() {
    // // You fill this in
    // }
    //
    // /**
    // * This class is responsible for detecting when the buttons are
    // * clicked or interactors are used, so you will have to add code
    // * to respond to these actions.
    // */
    // public void actionPerformed(ActionEvent e) {
    // // You fill this in as well as add any additional methods
    // }

}
