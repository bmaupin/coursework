import javafx.application.Application;
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
        FacePamphletCanvas canvas = new FacePamphletCanvas(primaryStage);
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
