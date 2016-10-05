
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    private static final double GUESSED_WORD_LABEL_X = CANVAS_WIDTH / 8;
    private static final double GUESSED_WORD_LABEL_Y = VERTICAL_BEAM_START_Y
            + ((CANVAS_HEIGHT - VERTICAL_BEAM_START_Y) / 3);
    private static final int GUESSED_WORD_FONT_SIZE = 32;

    private static final double INCORRECT_GUESSES_LABEL_X = GUESSED_WORD_LABEL_X;
    private static final double INCORRECT_GUESSES_LABEL_Y = GUESSED_WORD_LABEL_Y + 50;
    private static final double INCORRECT_GUESSES_FONT_SIZE = 18;

    private List<Node> bodyParts;
    private Text guessedWordLabel;
    private Text incorrectGuessesLabel;
    private int numBodyPartsShown;
    private Group rootGroup;

    public HangmanCanvas(Stage primaryStage) {
        rootGroup = new Group();

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        rootGroup.getChildren().add(canvas);

        primaryStage.setScene(new Scene(rootGroup));
        primaryStage.show();

        bodyParts = new ArrayList<Node>();
        bodyParts.add(createHead());
        bodyParts.add(createBody());
        bodyParts.add(createLeftArm());
        bodyParts.add(createRightArm());
        bodyParts.add(createLeftLeg());
        bodyParts.add(createRightLeg());
        bodyParts.add(createLeftFoot());
        bodyParts.add(createRightFoot());
    }

    private Circle createHead() {
        Circle head = new Circle(HEAD_CENTER_X, HEAD_CENTER_Y, HEAD_RADIUS, Color.TRANSPARENT);
        head.setStroke(Color.BLACK);
        return head;
    }

    private Line createBody() {
        Line body = new Line(BODY_START_X, BODY_START_Y, BODY_END_X, BODY_END_Y);
        return body;
    }

    private Group createLeftArm() {
        Line leftUpperArm = new Line(LEFT_UPPER_ARM_START_X, LEFT_UPPER_ARM_START_Y, LEFT_UPPER_ARM_END_X,
                LEFT_UPPER_ARM_END_Y);
        Line leftLowerArm = new Line(LEFT_LOWER_ARM_START_X, LEFT_LOWER_ARM_START_Y, LEFT_LOWER_ARM_END_X,
                LEFT_LOWER_ARM_END_Y);

        Group leftArm = new Group(leftUpperArm, leftLowerArm);
        return leftArm;
    }

    private Group createRightArm() {
        Line rightUpperArm = new Line(RIGHT_UPPER_ARM_START_X, RIGHT_UPPER_ARM_START_Y, RIGHT_UPPER_ARM_END_X,
                RIGHT_UPPER_ARM_END_Y);
        Line rightLowerArm = new Line(RIGHT_LOWER_ARM_START_X, RIGHT_LOWER_ARM_START_Y, RIGHT_LOWER_ARM_END_X,
                RIGHT_LOWER_ARM_END_Y);

        Group rightArm = new Group(rightUpperArm, rightLowerArm);
        return rightArm;
    }

    private Group createLeftLeg() {
        Line leftUpperLeg = new Line(LEFT_UPPER_LEG_START_X, LEFT_UPPER_LEG_START_Y, LEFT_UPPER_LEG_END_X,
                LEFT_UPPER_LEG_END_Y);
        Line leftLowerLeg = new Line(LEFT_LOWER_LEG_START_X, LEFT_LOWER_LEG_START_Y, LEFT_LOWER_LEG_END_X,
                LEFT_LOWER_LEG_END_Y);

        Group leftLeg = new Group(leftUpperLeg, leftLowerLeg);
        return leftLeg;
    }

    private Group createRightLeg() {
        Line rightUpperLeg = new Line(RIGHT_UPPER_LEG_START_X, RIGHT_UPPER_LEG_START_Y, RIGHT_UPPER_LEG_END_X,
                RIGHT_UPPER_LEG_END_Y);
        Line rightLowerLeg = new Line(RIGHT_LOWER_LEG_START_X, RIGHT_LOWER_LEG_START_Y, RIGHT_LOWER_LEG_END_X,
                RIGHT_LOWER_LEG_END_Y);

        Group rightLeg = new Group(rightUpperLeg, rightLowerLeg);
        return rightLeg;
    }

    private Line createLeftFoot() {
        Line leftFoot = new Line(LEFT_FOOT_START_X, LEFT_FOOT_START_Y, LEFT_FOOT_END_X, LEFT_FOOT_END_Y);
        return leftFoot;
    }

    private Line createRightFoot() {
        Line rightFoot = new Line(RIGHT_FOOT_START_X, RIGHT_FOOT_START_Y, RIGHT_FOOT_END_X, RIGHT_FOOT_END_Y);
        return rightFoot;
    }

    /** Resets the display so that only the scaffold appears */
    public void reset() {
        drawScaffold();
        numBodyPartsShown = 0;
        guessedWordLabel = createGuessedWordLabel();
        incorrectGuessesLabel = createIncorrectGuessesLabel();
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

    private Text createGuessedWordLabel() {
        Text guessedWordLabel = new Text(GUESSED_WORD_LABEL_X, GUESSED_WORD_LABEL_Y, "");
        guessedWordLabel.setFont(new Font(GUESSED_WORD_FONT_SIZE));

        return guessedWordLabel;
    }

    private Text createIncorrectGuessesLabel() {
        Text incorrectGuessesLabel = new Text(INCORRECT_GUESSES_LABEL_X, INCORRECT_GUESSES_LABEL_Y, "");
        incorrectGuessesLabel.setFont(new Font(INCORRECT_GUESSES_FONT_SIZE));

        return incorrectGuessesLabel;
    }

    /**
     * Updates the word on the screen to correspond to the current state of the
     * game. The argument string shows what letters have been guessed so far;
     * unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        updateGuessedWord(word);
    }

    private void updateGuessedWord(String guessedWord) {
        rootGroup.getChildren().remove(guessedWordLabel);
        guessedWordLabel.setText(guessedWord);
        rootGroup.getChildren().add(guessedWordLabel);
    }

    /**
     * Updates the display to correspond to an incorrect guess by the user.
     * Calling this method causes the next body part to appear on the scaffold
     * and adds the letter to the list of incorrect guesses that appears at the
     * bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        addNextBodyPart();
        updateIncorrectGuesses(letter);
    }

    private void addNextBodyPart() {
        if (numBodyPartsShown < bodyParts.size()) {
            Node bodyPartToAdd = bodyParts.get(numBodyPartsShown);

            rootGroup.getChildren().add(bodyPartToAdd);
            numBodyPartsShown++;
        }
    }

    private void updateIncorrectGuesses(char letter) {
        rootGroup.getChildren().remove(incorrectGuessesLabel);
        incorrectGuessesLabel.setText(incorrectGuessesLabel.getText() + letter);
        rootGroup.getChildren().add(incorrectGuessesLabel);
    }
}
