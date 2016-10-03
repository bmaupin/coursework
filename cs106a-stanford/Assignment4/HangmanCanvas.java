
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

    private static final double VERTICAL_BEAM_START_X = CANVAS_WIDTH / 2 - BEAM_LENGTH;
    private static final double VERTICAL_BEAM_END_X = VERTICAL_BEAM_START_X;
    private static final double VERTICAL_BEAM_START_Y = (CANVAS_HEIGHT - SCAFFOLD_HEIGHT) * 1 / 3 + SCAFFOLD_HEIGHT;
    private static final double VERTICAL_BEAM_END_Y = VERTICAL_BEAM_START_Y - SCAFFOLD_HEIGHT;

    private static final double HORIZONTAL_BEAM_START_X = VERTICAL_BEAM_END_X;
    private static final double HORIZONTAL_BEAM_END_X = HORIZONTAL_BEAM_START_X + BEAM_LENGTH;
    private static final double HORIZONTAL_BEAM_START_Y = VERTICAL_BEAM_END_Y;
    private static final double HORIZONTAL_BEAM_END_Y = HORIZONTAL_BEAM_START_Y;

    private static final double ROPE_START_X = HORIZONTAL_BEAM_END_X;
    private static final double ROPE_END_X = ROPE_START_X;
    private static final double ROPE_START_Y = HORIZONTAL_BEAM_END_Y;
    private static final double ROPE_END_Y = ROPE_START_Y + ROPE_LENGTH;

    private static final double HEAD_CENTER_X = ROPE_END_X;
    private static final double HEAD_CENTER_Y = ROPE_END_Y + HEAD_RADIUS;

    private static final double BODY_START_X = HEAD_CENTER_X;
    private static final double BODY_END_X = HEAD_CENTER_X;
    private static final double BODY_START_Y = HEAD_CENTER_Y + HEAD_RADIUS;
    private static final double BODY_END_Y = BODY_START_Y + BODY_LENGTH;

    private static final double LEFT_UPPER_ARM_START_X = BODY_START_X;
    private static final double LEFT_UPPER_ARM_END_X = LEFT_UPPER_ARM_START_X - UPPER_ARM_LENGTH;
    private static final double LEFT_UPPER_ARM_START_Y = BODY_START_Y + ARM_OFFSET_FROM_HEAD;
    private static final double LEFT_UPPER_ARM_END_Y = LEFT_UPPER_ARM_START_Y;

    private static final double LEFT_LOWER_ARM_START_X = LEFT_UPPER_ARM_END_X;
    private static final double LEFT_LOWER_ARM_END_X = LEFT_LOWER_ARM_START_X;
    private static final double LEFT_LOWER_ARM_START_Y = LEFT_UPPER_ARM_END_Y;
    private static final double LEFT_LOWER_ARM_END_Y = LEFT_LOWER_ARM_START_Y + LOWER_ARM_LENGTH;

    private static final double RIGHT_UPPER_ARM_START_X = LEFT_UPPER_ARM_START_X;
    private static final double RIGHT_UPPER_ARM_END_X = RIGHT_UPPER_ARM_START_X + UPPER_ARM_LENGTH;
    private static final double RIGHT_UPPER_ARM_START_Y = LEFT_UPPER_ARM_START_Y;
    private static final double RIGHT_UPPER_ARM_END_Y = RIGHT_UPPER_ARM_START_Y;

    private static final double RIGHT_LOWER_ARM_START_X = RIGHT_UPPER_ARM_END_X;
    private static final double RIGHT_LOWER_ARM_END_X = RIGHT_LOWER_ARM_START_X;
    private static final double RIGHT_LOWER_ARM_START_Y = RIGHT_UPPER_ARM_END_Y;
    private static final double RIGHT_LOWER_ARM_END_Y = RIGHT_LOWER_ARM_START_Y + LOWER_ARM_LENGTH;

    private static final double LEFT_UPPER_LEG_START_X = BODY_END_X;
    private static final double LEFT_UPPER_LEG_END_X = LEFT_UPPER_LEG_START_X - HIP_WIDTH;
    private static final double LEFT_UPPER_LEG_START_Y = BODY_END_Y;
    private static final double LEFT_UPPER_LEG_END_Y = LEFT_UPPER_LEG_START_Y;

    private static final double LEFT_LOWER_LEG_START_X = LEFT_UPPER_LEG_END_X;
    private static final double LEFT_LOWER_LEG_END_X = LEFT_LOWER_LEG_START_X;
    private static final double LEFT_LOWER_LEG_START_Y = LEFT_UPPER_LEG_END_Y;
    private static final double LEFT_LOWER_LEG_END_Y = LEFT_LOWER_LEG_START_Y + LEG_LENGTH;

    private static final double RIGHT_UPPER_LEG_START_X = LEFT_UPPER_LEG_START_X;
    private static final double RIGHT_UPPER_LEG_END_X = RIGHT_UPPER_LEG_START_X + HIP_WIDTH;
    private static final double RIGHT_UPPER_LEG_START_Y = LEFT_UPPER_LEG_START_Y;
    private static final double RIGHT_UPPER_LEG_END_Y = RIGHT_UPPER_LEG_START_Y;

    private static final double RIGHT_LOWER_LEG_START_X = RIGHT_UPPER_LEG_END_X;
    private static final double RIGHT_LOWER_LEG_END_X = RIGHT_LOWER_LEG_START_X;
    private static final double RIGHT_LOWER_LEG_START_Y = RIGHT_UPPER_LEG_END_Y;
    private static final double RIGHT_LOWER_LEG_END_Y = RIGHT_LOWER_LEG_START_Y + LEG_LENGTH;

    private static final double LEFT_FOOT_START_X = LEFT_LOWER_LEG_END_X;
    private static final double LEFT_FOOT_END_X = LEFT_FOOT_START_X - FOOT_LENGTH;
    private static final double LEFT_FOOT_START_Y = LEFT_LOWER_LEG_END_Y;
    private static final double LEFT_FOOT_END_Y = LEFT_FOOT_START_Y;

    private static final double RIGHT_FOOT_START_X = RIGHT_LOWER_LEG_END_X;
    private static final double RIGHT_FOOT_END_X = RIGHT_FOOT_START_X + FOOT_LENGTH;
    private static final double RIGHT_FOOT_START_Y = RIGHT_LOWER_LEG_END_Y;
    private static final double RIGHT_FOOT_END_Y = RIGHT_FOOT_START_Y;

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
        // TODO: implement this
    }

    /**
     * Updates the display to correspond to an incorrect guess by the user.
     * Calling this method causes the next body part to appear on the scaffold
     * and adds the letter to the list of incorrect guesses that appears at the
     * bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        // TODO: add each body part one at a time
        drawHead();
        drawBody();
        drawLeftArm();
        drawRightArm();
        drawLeftLeg();
        drawRightLeg();
        drawLeftFoot();
        drawRightFoot();
    }

    private void drawScaffold() {
        drawVerticalBeam();
        drawHorizontalBeam();
        drawRope();
    }

    private void drawVerticalBeam() {
        Line verticalBeam = new Line(VERTICAL_BEAM_START_X, VERTICAL_BEAM_START_Y, VERTICAL_BEAM_END_X,
                VERTICAL_BEAM_END_Y);
        rootGroup.getChildren().add(verticalBeam);
    }

    private void drawHorizontalBeam() {
        Line horizontalBeam = new Line(HORIZONTAL_BEAM_START_X, HORIZONTAL_BEAM_START_Y, HORIZONTAL_BEAM_END_X,
                HORIZONTAL_BEAM_END_Y);
        rootGroup.getChildren().add(horizontalBeam);
    }

    private void drawRope() {
        Line rope = new Line(ROPE_START_X, ROPE_START_Y, ROPE_END_X, ROPE_END_Y);
        rootGroup.getChildren().add(rope);
    }

    private void drawHead() {
        Circle head = new Circle(HEAD_CENTER_X, HEAD_CENTER_Y, HEAD_RADIUS, Color.TRANSPARENT);
        head.setStroke(Color.BLACK);
        rootGroup.getChildren().add(head);
    }

    private void drawBody() {
        Line body = new Line(BODY_START_X, BODY_START_Y, BODY_END_X, BODY_END_Y);
        rootGroup.getChildren().add(body);
    }

    private void drawLeftArm() {
        Line leftUpperArm = new Line(LEFT_UPPER_ARM_START_X, LEFT_UPPER_ARM_START_Y, LEFT_UPPER_ARM_END_X,
                LEFT_UPPER_ARM_END_Y);
        Line leftLowerArm = new Line(LEFT_LOWER_ARM_START_X, LEFT_LOWER_ARM_START_Y, LEFT_LOWER_ARM_END_X,
                LEFT_LOWER_ARM_END_Y);

        Group leftArm = new Group(leftUpperArm, leftLowerArm);
        rootGroup.getChildren().add(leftArm);
    }

    private void drawRightArm() {
        Line rightUpperArm = new Line(RIGHT_UPPER_ARM_START_X, RIGHT_UPPER_ARM_START_Y, RIGHT_UPPER_ARM_END_X,
                RIGHT_UPPER_ARM_END_Y);
        Line rightLowerArm = new Line(RIGHT_LOWER_ARM_START_X, RIGHT_LOWER_ARM_START_Y, RIGHT_LOWER_ARM_END_X,
                RIGHT_LOWER_ARM_END_Y);

        Group rightArm = new Group(rightUpperArm, rightLowerArm);
        rootGroup.getChildren().add(rightArm);
    }

    private void drawLeftLeg() {
        Line leftUpperLeg = new Line(LEFT_UPPER_LEG_START_X, LEFT_UPPER_LEG_START_Y, LEFT_UPPER_LEG_END_X,
                LEFT_UPPER_LEG_END_Y);
        Line leftLowerLeg = new Line(LEFT_LOWER_LEG_START_X, LEFT_LOWER_LEG_START_Y, LEFT_LOWER_LEG_END_X,
                LEFT_LOWER_LEG_END_Y);

        Group leftLeg = new Group(leftUpperLeg, leftLowerLeg);
        rootGroup.getChildren().add(leftLeg);
    }

    private void drawRightLeg() {
        Line rightUpperLeg = new Line(RIGHT_UPPER_LEG_START_X, RIGHT_UPPER_LEG_START_Y, RIGHT_UPPER_LEG_END_X,
                RIGHT_UPPER_LEG_END_Y);
        Line rightLowerLeg = new Line(RIGHT_LOWER_LEG_START_X, RIGHT_LOWER_LEG_START_Y, RIGHT_LOWER_LEG_END_X,
                RIGHT_LOWER_LEG_END_Y);

        Group rightLeg = new Group(rightUpperLeg, rightLowerLeg);
        rootGroup.getChildren().add(rightLeg);
    }

    private void drawLeftFoot() {
        Line leftFoot = new Line(LEFT_FOOT_START_X, LEFT_FOOT_START_Y, LEFT_FOOT_END_X, LEFT_FOOT_END_Y);
        rootGroup.getChildren().add(leftFoot);
    }

    private void drawRightFoot() {
        Line rightFoot = new Line(RIGHT_FOOT_START_X, RIGHT_FOOT_START_Y, RIGHT_FOOT_END_X, RIGHT_FOOT_END_Y);
        rootGroup.getChildren().add(rightFoot);
    }
}
