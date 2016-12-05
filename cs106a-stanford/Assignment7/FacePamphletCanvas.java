import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private static final int PROFILE_NAME_FONT_SIZE = 24;
    private static final int PROFILE_FRIENDS_LIST_FONT_SIZE = 16;
    private static final int PROFILE_STATUS_FONT_SIZE = 16;

    private Text profileFriendsList;
    private ImageView profileImage;
    private Text profileNameLabel;
    private Text profileStatusLabel;
    private Label messageLabel;

    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas() {
        VBox leftProfilePane = createLeftProfilePane();

        this.setCenter(leftProfilePane);
        BorderPane.setMargin(leftProfilePane, new Insets(0, 0, 0, LEFT_MARGIN));

        profileFriendsList = new Text();
        profileFriendsList.setFont(new Font(PROFILE_FRIENDS_LIST_FONT_SIZE));

        this.setRight(profileFriendsList);

        messageLabel = new Label();
        messageLabel.setFont(new Font(MESSAGE_FONT_SIZE));

        this.setBottom(messageLabel);
        BorderPane.setMargin(messageLabel, new Insets(0, 0, BOTTOM_MESSAGE_MARGIN, LEFT_MARGIN));
    }

    private VBox createLeftProfilePane() {
        VBox leftProfilePane = new VBox();

        profileNameLabel = new Text();
        profileNameLabel.setFont(new Font(PROFILE_NAME_FONT_SIZE));
        profileNameLabel.setFill(Color.BLUE);
        VBox.setMargin(profileNameLabel, new Insets(TOP_MARGIN, 0, 0, 0));

        profileImage = new ImageView();
        profileImage.setFitHeight(IMAGE_HEIGHT);
        profileImage.setFitWidth(IMAGE_WIDTH);
        VBox.setMargin(profileImage, new Insets(IMAGE_MARGIN, 0, 0, 0));

        profileStatusLabel = new Text();
        profileStatusLabel.setFont(new Font(PROFILE_STATUS_FONT_SIZE));
        VBox.setMargin(profileStatusLabel, new Insets(STATUS_MARGIN, 0, 0, 0));

        leftProfilePane.getChildren().addAll(profileNameLabel, profileImage, profileStatusLabel);

        return leftProfilePane;
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
        showMessage("");

        profileNameLabel.setText(profile.getName());
        profileStatusLabel.setText(profile.getStatus());
        profileImage.setImage(profile.getImage());

        // TODO: show friends
    }

    public void clearProfile() {
        profileFriendsList.setText("");
        profileImage.setImage(null);
        profileNameLabel.setText("");
        profileStatusLabel.setText("");
    }
}
