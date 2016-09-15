
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Breakout extends Application {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    // Maximum value of x the paddle should have so it doesn't go off the screen
    private static final int PADDLE_MAX_X = APPLICATION_WIDTH - PADDLE_WIDTH;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    private List<Rectangle> bricks;
    private Color[] brickColors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };

    private Circle ball;
    private AnimationTimer ballAnimation;
    private double ballVelocityX;
    private double ballVelocityY = 3.0;

    private Rectangle paddle;

    private Group root;

    private int turnsLeft = NTURNS;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpGame(primaryStage);
        playGame();
    }

    // TODO: break this function up
    private void setUpGame(Stage primaryStage) {
        root = new Group();

        Rectangle wall = createWall();
        root.getChildren().add(wall);

        bricks = createBricks();
        root.getChildren().addAll(bricks);

        paddle = createPaddle();
        root.getChildren().add(paddle);

        ball = createBall();
        root.getChildren().add(ball);

        // Track mouse movement
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double paddleX = event.getSceneX();

                // Don't let the paddle go off the right edge of the screen
                if (paddleX > PADDLE_MAX_X) {
                    paddleX = PADDLE_MAX_X;
                }

                paddle.setX(paddleX);
            }
        });

        Scene scene = new Scene(root);

        // Hide the mouse cursor
        scene.setCursor(Cursor.NONE);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Rectangle createWall() {
        final Rectangle wall = new Rectangle(0, 0, WIDTH, HEIGHT);
        // Default fill for a rectangle is black, so we need to remove it
        wall.setFill(Color.TRANSPARENT);
        wall.setStroke(Color.BLACK);
        return wall;
    }

    private List<Rectangle> createBricks() {
        List<Rectangle> bricks = new ArrayList<Rectangle>();

        for (int row = 0; row < NBRICKS_PER_ROW; row++) {
            for (int col = 0; col < NBRICK_ROWS; col++) {
                int x = (APPLICATION_WIDTH - NBRICKS_PER_ROW * BRICK_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2
                        + (BRICK_WIDTH + BRICK_SEP) * col;
                int y = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * row;

                bricks.add(createBrick(x, y, brickColors[row / 2]));
            }
        }

        return bricks;
    }

    private Rectangle createBrick(int x, int y, Color color) {
        final Rectangle brick = new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFill(color);
        return brick;
    }

    private Rectangle createPaddle() {
        int x = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
        int y = APPLICATION_HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET;

        final Rectangle paddle = new Rectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFill(Color.BLACK);
        return paddle;
    }

    private Circle createBall() {
        // x and y are the center coordinates of the circle
        int x = APPLICATION_WIDTH / 2;
        int y = APPLICATION_HEIGHT / 2;

        final Circle ball = new Circle(x, y, BALL_RADIUS, Color.BLACK);
        return ball;
    }

    private void playGame() {
        // Generate a random velocity between 1.0 and 3.0
        ballVelocityX = new Random().nextDouble() * 2 + 1;
        // TODO initialize ballVeloxityX here too

        ballAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkWallCollision();
                checkPaddleCollision();
                checkBrickCollision();

                ball.setCenterX(ball.getCenterX() + ballVelocityX);
                ball.setCenterY(ball.getCenterY() + ballVelocityY);
            }
        };
        ballAnimation.start();
    }

    private void checkWallCollision() {
        // Check to see whether the ball's hit the right or left wall
        if (ball.getCenterX() - BALL_RADIUS <= 0 || ball.getCenterX() + BALL_RADIUS >= APPLICATION_WIDTH) {
            ballVelocityX = -ballVelocityX;
        }

        // Check to see whether the ball's hit the top wall
        if (ball.getCenterY() - BALL_RADIUS <= 0) {
            // Change direction
            ballVelocityY = -ballVelocityY;
        }

        // Check to see whether the ball's hit the bottom wall
        if (ball.getCenterY() + BALL_RADIUS >= APPLICATION_HEIGHT) {
            endTurn();
        }
    }

    private void checkPaddleCollision() {
        // Only check paddle collisions if the ball is moving downward. This
        // keeps the ball from getting stuck on the paddle
        if (ballVelocityY > 0) {
            if (ball.getBoundsInLocal().intersects(paddle.getBoundsInLocal())) {
                ballVelocityY = -ballVelocityY;
            }
        }
    }

    private void checkBrickCollision() {
        for (Rectangle brick : bricks) {
            if (ball.getBoundsInLocal().intersects(brick.getBoundsInLocal())) {
                root.getChildren().remove(brick);
                bricks.remove(brick);
                ballVelocityY = -ballVelocityY;

                // Only check collision with one brick to keep things simple
                // (this seems to fulfill the assignment requirements)
                break;
            }
        }
    }

    private void endTurn() {
        turnsLeft--;
        ballAnimation.stop();

        if (turnsLeft > 0) {
            restartGameOnClick();
        } else {
            displayGameOverText();
        }
    }

    private void displayGameOverText() {
        final Text gameOverText = new Text("Game over");

        // Put the text in a StackPane so it will be centered in the game window
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        stackPane.getChildren().add(gameOverText);

        root.getChildren().add(stackPane);
    }

    private void restartGameOnClick() {
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Recreate the ball at its starting location
                root.getChildren().remove(ball);
                ball = createBall();
                root.getChildren().add(ball);

                ballAnimation.start();

                // Stop listening for mouse clicks
                root.setOnMouseClicked(null);
            }
        });
    }
}
