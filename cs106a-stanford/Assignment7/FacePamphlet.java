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

public class FacePamphlet extends Application implements FacePamphletConstants {
    private FacePamphletProfile currentProfile;
    private FacePamphletDatabase database;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpDatabase();
        setUpUi(primaryStage);
    }

    private void setUpDatabase() {
        database = new FacePamphletDatabase();
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
        Text labelName = new Text("Name");

        TextField inputName = new TextField();

        Button buttonAdd = new Button("Add");
        buttonAdd.setPrefSize(100, 20);
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addProfile(inputName.getText());
            }
        });

        Button buttonDelete = new Button("Delete");
        buttonDelete.setPrefSize(100, 20);
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteProfile(inputName.getText());
            }
        });

        Button buttonLookup = new Button("Lookup");
        buttonLookup.setPrefSize(100, 20);
        buttonLookup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lookupProfile(inputName.getText());
            }
        });

        topPane.getChildren().addAll(labelName, inputName, buttonAdd, buttonDelete, buttonLookup);
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

    private void addProfile(String profileName) {
        if (database.containsProfile(profileName)) {
            currentProfile = database.getProfile(profileName);

        } else {
            FacePamphletProfile profile = new FacePamphletProfile(profileName);
            currentProfile = profile;
        }

        // TODO: show the current profile
    }

    private void deleteProfile(String profileName) {
        database.deleteProfile(profileName);
        currentProfile = null;

        // TODO: clear the current profile
    }

    private void lookupProfile(String profileName) {
        if (database.containsProfile(profileName)) {
            currentProfile = database.getProfile(profileName);

        } else {
            currentProfile = null;
        }

        // TODO: show the current profile
    }

    private void changeStatus(String status) {
        if (currentProfile != null) {
            currentProfile.setStatus(status);

        } else {
            // TODO: prompt the user to select a profile
        }
    }

    private void changePicture(String filename) {
        // TODO
    }

    private void addFriend(String friendName) {
        if (currentProfile != null) {
            if (database.containsProfile(friendName)) {
                if (currentProfile.addFriend(friendName)) {
                    database.getProfile(friendName).addFriend(currentProfile.getName());

                } else {
                    // TODO: If the named friend already exists in the list of
                    // friends for the current profile, then we simply write out
                    // a message that such a friend already exists.
                }
            } else {
                // TODO: If the name entered in the Add Friend text field is not
                // a
                // valid profile in the system, we should just print out a
                // message
                // to that effect.
            }
        } else {
            // TODO: If there is no current profile, then you should simply
            // prompt the user to select a profile to add a friend to (and there
            // should be no changes to any of the profiles in the database).
        }
    }
}
