import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private FacePamphletCanvas mainPane;

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

        mainPane = createMainPane();
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
                    changeStatus(inputChangeStatus.getText());
                }
            }
        });

        Button buttonChangeStatus = new Button("Change Status");
        setHeightWidthToFit(buttonChangeStatus);
        buttonChangeStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStatus(inputChangeStatus.getText());
            }
        });
        VBox.setMargin(buttonChangeStatus, new Insets(0, 0, 20, 0));

        TextField inputChangePicture = new TextField();
        inputChangePicture.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    changePicture(inputChangePicture.getText());
                }
            }
        });

        Button buttonChangePicture = new Button("Change Picture");
        setHeightWidthToFit(buttonChangePicture);
        buttonChangePicture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changePicture(inputChangePicture.getText());
            }
        });
        VBox.setMargin(buttonChangePicture, new Insets(0, 0, 20, 0));

        TextField inputAddFriend = new TextField();
        inputAddFriend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    addFriend(inputAddFriend.getText());
                }
            }
        });

        Button buttonAddFriend = new Button("Add Friend");
        setHeightWidthToFit(buttonAddFriend);
        buttonAddFriend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addFriend(inputAddFriend.getText());
            }
        });

        leftPane.getChildren().addAll(inputChangeStatus, buttonChangeStatus, inputChangePicture, buttonChangePicture,
                inputAddFriend, buttonAddFriend);
    }

    private void setHeightWidthToFit(Region region) {
        region.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    private FacePamphletCanvas createMainPane() {
        FacePamphletCanvas mainPane = new FacePamphletCanvas();
        setWhiteBackground(mainPane);

        return mainPane;
    }

    private void setWhiteBackground(Pane pane) {
        pane.setStyle("-fx-background-color: white");
    }

    private void addProfile(String profileName) {
        if (database.containsProfile(profileName)) {
            currentProfile = database.getProfile(profileName);

        } else {
            FacePamphletProfile profile = new FacePamphletProfile(profileName);
            database.addProfile(profile);
            currentProfile = profile;
        }

        mainPane.displayProfile(currentProfile);
    }

    private void deleteProfile(String profileName) {
        if (database.containsProfile(profileName)) {
            database.deleteProfile(profileName);
            mainPane.showMessage(String.format("Profile of %s deleted", profileName));

        } else {
            mainPane.showMessage(String.format("A profile with the name %s does not exist", profileName));
        }

        currentProfile = null;
        mainPane.clearProfile();
    }

    private void lookupProfile(String profileName) {
        if (database.containsProfile(profileName)) {
            currentProfile = database.getProfile(profileName);
            mainPane.displayProfile(currentProfile);
            mainPane.showMessage(String.format("Displaying %s", profileName));

        } else {
            currentProfile = null;
            mainPane.clearProfile();
            mainPane.showMessage(String.format("A profile with the name %s does not exist", profileName));
        }
    }

    private void changeStatus(String status) {
        if (currentProfile != null) {
            currentProfile.setStatus(status);
            mainPane.displayProfile(currentProfile);
            mainPane.showMessage(String.format("Status updated to %s", status));

        } else {
            mainPane.showMessage("Please select a profile to change status");
        }
    }

    private void changePicture(String filename) {
        if (currentProfile != null) {
            try {
                Image profileImage = new Image(String.format("images/%s", filename));
                currentProfile.setImage(profileImage);
                mainPane.displayProfile(currentProfile);
                mainPane.showMessage("Picture updated");

            } catch (IllegalArgumentException e) {
                mainPane.showMessage(String.format("Unable to open image file: %s", filename));
            }

        } else {
            mainPane.showMessage("Please select a profile to change picture");
        }
    }

    private void addFriend(String friendName) {
        if (currentProfile != null) {
            if (database.containsProfile(friendName)) {
                if (currentProfile.addFriend(friendName)) {
                    database.getProfile(friendName).addFriend(currentProfile.getName());
                    mainPane.displayProfile(currentProfile);

                } else {
                    mainPane.showMessage(
                            String.format("%s already has %s as a friend", currentProfile.getName(), friendName));
                }
            } else {
                mainPane.showMessage(String.format("%s does not exist", friendName));
            }
        } else {
            mainPane.showMessage("Please select a profile to add friend");
        }
    }
}
