
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// This isn't really a canvas but we'll keep the class name to stay as close to the assignment as possible
public class FacePamphletCanvas implements FacePamphletConstants {
    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas(Stage primaryStage) {
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
        topPane.setPadding(new Insets(15, 12, 15, 12));
        topPane.setSpacing(10);
        topPane.setAlignment(Pos.CENTER);

        addComponentsToBottomPane(topPane);

        return topPane;
    }

    private void addComponentsToBottomPane(HBox bottomPane) {
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

        bottomPane.getChildren().addAll(nameLabel, nameInput, buttonAdd, buttonDelete, buttonLookup);
    }

    private VBox createLeftPane() {
        VBox leftPane = new VBox();
        leftPane.setPadding(new Insets(15, 12, 15, 12));
        leftPane.setSpacing(10);
        leftPane.setPrefHeight(APPLICATION_HEIGHT);
        leftPane.setAlignment(Pos.CENTER);

        TextField inputChangeStatus = new TextField();

        Button buttonChangeStatus = new Button("Change Status");
        setHeightWidthToFit(buttonChangeStatus);
        buttonChangeStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        TextField inputChangePicture = new TextField();

        Button buttonChangePicture = new Button("Change Picture");
        setHeightWidthToFit(buttonChangePicture);
        buttonChangePicture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
            }
        });

        TextField inputAddFriend = new TextField();

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

        return leftPane;
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

    /**
     * This method displays a message string near the bottom of the canvas.
     * Every time this method is called, the previously displayed message (if
     * any) is replaced by the new message text passed in.
     */
    public void showMessage(String msg) {
        // You fill this in
    }

    /**
     * This method displays the given profile on the canvas. The canvas is first
     * cleared of all existing items (including messages displayed near the
     * bottom of the screen) and then the given profile is displayed. The
     * profile display includes the name of the user from the profile, the
     * corresponding image (or an indication that an image does not exist), the
     * status of the user, and a list of the user's friends in the social
     * network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        // You fill this in
    }

}
