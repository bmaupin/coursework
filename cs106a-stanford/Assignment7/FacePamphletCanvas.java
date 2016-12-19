import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

// This isn't really a canvas but we'll keep the class name to stay as close to the assignment as possible
public class FacePamphletCanvas extends BorderPane implements FacePamphletConstants {
    private static final int MESSAGE_FONT_SIZE = 18;
    private static final int PROFILE_IMAGE_FONT_SIZE = 24;
    private static final int PROFILE_NAME_FONT_SIZE = 24;
    private static final int PROFILE_FRIENDS_LABEL_FONT_SIZE = 16;
    private static final int PROFILE_FRIENDS_LIST_FONT_SIZE = 16;
    private static final int PROFILE_STATUS_FONT_SIZE = 16;

    private Text profileFriendsLabel;
    private Text profileFriendsList;
    private Pane profileImage;
    private Text profileNameLabel;
    private Text profileStatusLabel;
    private Label messageLabel;

    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas() {
        profileNameLabel = createProfileNameLabel();
        this.setTop(profileNameLabel);

        HBox centerProfilePane = createCenterProfilePane();
        this.setCenter(centerProfilePane);

        HBox messagePane = createMessagePane();
        this.setBottom(messagePane);
    }

    private Text createProfileNameLabel() {
        Text profileNameLabel = new Text();
        profileNameLabel.setFont(new Font(PROFILE_NAME_FONT_SIZE));
        profileNameLabel.setFill(Color.BLUE);
        BorderPane.setMargin(profileNameLabel, new Insets(TOP_MARGIN, 0, 0, LEFT_MARGIN));

        return profileNameLabel;
    }

    private HBox createCenterProfilePane() {
        HBox centerProfilePane = new HBox();
        BorderPane.setMargin(centerProfilePane, new Insets(0, 0, 0, LEFT_MARGIN));

        VBox leftProfilePane = createLeftProfilePane();
        VBox rightProfilePane = createRightProfilePane();

        // Make sure center pane child panes are the same width
        leftProfilePane.prefWidthProperty().bind(centerProfilePane.widthProperty().divide(2));
        rightProfilePane.prefWidthProperty().bind(centerProfilePane.widthProperty().divide(2));

        centerProfilePane.getChildren().addAll(leftProfilePane, rightProfilePane);

        return centerProfilePane;
    }

    private VBox createLeftProfilePane() {
        VBox leftProfilePane = new VBox();

        profileImage = new Pane();
        profileImage.setPrefHeight(IMAGE_HEIGHT);
        profileImage.setPrefWidth(IMAGE_WIDTH);
        profileImage.setMaxHeight(IMAGE_HEIGHT);
        profileImage.setMaxWidth(IMAGE_WIDTH);
        VBox.setMargin(profileImage, new Insets(IMAGE_MARGIN, 0, 0, 0));

        profileStatusLabel = new Text();
        profileStatusLabel.setFont(new Font(PROFILE_STATUS_FONT_SIZE));
        profileStatusLabel.setStyle("-fx-font-weight: bold");
        VBox.setMargin(profileStatusLabel, new Insets(STATUS_MARGIN, 0, 0, 0));

        leftProfilePane.getChildren().addAll(profileImage, profileStatusLabel);

        return leftProfilePane;
    }

    private VBox createRightProfilePane() {
        VBox rightProfilePane = new VBox();

        profileFriendsLabel = new Text();
        profileFriendsLabel.setFont(new Font(PROFILE_FRIENDS_LABEL_FONT_SIZE));
        profileFriendsLabel.setStyle("-fx-font-weight: bold");

        profileFriendsList = new Text();
        profileFriendsList.setFont(new Font(PROFILE_FRIENDS_LIST_FONT_SIZE));

        rightProfilePane.getChildren().addAll(profileFriendsLabel, profileFriendsList);

        return rightProfilePane;
    }

    private HBox createMessagePane() {
        HBox messagePane = new HBox();
        messagePane.setAlignment(Pos.CENTER);
        BorderPane.setMargin(messagePane, new Insets(0, 0, BOTTOM_MESSAGE_MARGIN, LEFT_MARGIN));

        messageLabel = createMessageLabel();
        messagePane.getChildren().addAll(messageLabel);

        return messagePane;
    }

    private Label createMessageLabel() {
        Label messageLabel = new Label();
        messageLabel.setFont(new Font(MESSAGE_FONT_SIZE));

        return messageLabel;
    }

    /**
     * This method displays a message string near the bottom of the canvas.
     * Every time this method is called, the previously displayed message (if
     * any) is replaced by the new message text passed in.
     */
    public void showMessage(String msg) {
        messageLabel.setText(msg);
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
        displayFriends(profile);
        displayImage(profile);
        displayName(profile);
        displayStatus(profile);
    }

    private void displayFriends(FacePamphletProfile profile) {
        profileFriendsLabel.setText("Friends:");

        List<String> friendsList = new ArrayList<String>();
        profile.getFriends().forEachRemaining(friendsList::add);
        profileFriendsList.setText(String.join("\n", friendsList));
    }

    private void displayImage(FacePamphletProfile profile) {
        clearImage();
        if (profile.getImage() == null) {
            profileImage.setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            final Text textNode = new Text("No Image");
            textNode.setFont(new Font(PROFILE_IMAGE_FONT_SIZE));

            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(IMAGE_WIDTH, IMAGE_HEIGHT);
            stackPane.getChildren().add(textNode);

            profileImage.getChildren().add(stackPane);

        } else {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(IMAGE_HEIGHT);
            imageView.setFitWidth(IMAGE_WIDTH);

            imageView.setImage(profile.getImage());
            profileImage.getChildren().add(imageView);
        }
    }

    private void clearImage() {
        profileImage.getChildren().clear();
        profileImage.setBorder(null);
    }

    private void displayName(FacePamphletProfile profile) {
        profileNameLabel.setText(profile.getName());
    }

    private void displayStatus(FacePamphletProfile profile) {
        if (profile.getStatus().equals("")) {
            profileStatusLabel.setText("No current status");
        } else {
            profileStatusLabel.setText(String.format("%s is %s", profile.getName(), profile.getStatus()));
        }
    }

    public void clearProfile() {
        profileFriendsLabel.setText("");
        profileFriendsList.setText("");
        clearImage();
        profileNameLabel.setText("");
        profileStatusLabel.setText("");
    }
}
