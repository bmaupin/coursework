
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HangmanCanvas {
    /* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;

    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 500;

    private Group rootGroup;

    public HangmanCanvas(Stage primaryStage) {
        rootGroup = new Group();

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        rootGroup.getChildren().add(canvas);

        primaryStage.setScene(new Scene(rootGroup));
        primaryStage.show();
    }

    /** Resets the display so that only the scaffold appears */
    public void reset() {
        drawScaffold();
    }

    /**
     * Updates the word on the screen to correspond to the current state of the
     * game. The argument string shows what letters have been guessed so far;
     * unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        /* You fill this in */
    }

    /**
     * Updates the display to correspond to an incorrect guess by the user.
     * Calling this method causes the next body part to appear on the scaffold
     * and adds the letter to the list of incorrect guesses that appears at the
     * bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        /* You fill this in */
    }

    private void drawScaffold() {
        drawVerticalBeam();
        drawHorizontalBeam();
        drawRope();
    }

    private void drawVerticalBeam() {
        double startX = CANVAS_WIDTH / 2 - BEAM_LENGTH;
        double endX = startX;
        double startY = (CANVAS_HEIGHT - SCAFFOLD_HEIGHT) * 1 / 3;
        double endY = startY + SCAFFOLD_HEIGHT;

        Line verticalBeam = new Line(startX, startY, endX, endY);

        rootGroup.getChildren().add(verticalBeam);
    }

    private void drawHorizontalBeam() {
        double startX = CANVAS_WIDTH / 2 - BEAM_LENGTH;
        double endX = startX + BEAM_LENGTH;
        double startY = (CANVAS_HEIGHT - SCAFFOLD_HEIGHT) * 1 / 3;
        double endY = startY;

        Line horizontalBeam = new Line(startX, startY, endX, endY);

        rootGroup.getChildren().add(horizontalBeam);
    }

    private void drawRope() {
        double startX = CANVAS_WIDTH / 2;
        double endX = startX;
        double startY = (CANVAS_HEIGHT - SCAFFOLD_HEIGHT) * 1 / 3;
        double endY = startY + ROPE_LENGTH;

        Line rope = new Line(startX, startY, endX, endY);

        rootGroup.getChildren().add(rope);
    }
}
