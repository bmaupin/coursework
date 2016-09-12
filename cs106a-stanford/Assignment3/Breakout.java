
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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

    private Color[] brickColors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };
    private double ballVelocityX;
    private double ballVelocityY = 3.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setUpGame(gc);

        // TODO: move this into setUpGame
        final Rectangle paddle = createPaddle();
        root.getChildren().add(paddle);

        final Circle ball = createBall();
        root.getChildren().add(ball);

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

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        // Hide the mouse cursor
        scene.setCursor(Cursor.NONE);

        primaryStage.setScene(scene);

        // Generate a random velocity between 1.0 and 3.0
        ballVelocityX = new Random().nextDouble() * 2 + 1;

        final AnimationTimer ballAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ball.setCenterX(ball.getCenterX() + ballVelocityX);
                ball.setCenterY(ball.getCenterY() + ballVelocityY);
            }
        };
        ballAnimation.start();

        checkCollision(ball, paddle);

        primaryStage.show();
    }

    private void setUpGame(GraphicsContext gc) {
        drawWall(gc);
        drawBricks(gc);
    }

    private void drawWall(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
    }

    private void drawBricks(GraphicsContext gc) {
        for (int row = 0; row < NBRICKS_PER_ROW; row++) {
            gc.setFill(brickColors[row / 2]);

            for (int col = 0; col < NBRICK_ROWS; col++) {
                int x = (APPLICATION_WIDTH - NBRICKS_PER_ROW * BRICK_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2
                        + (BRICK_WIDTH + BRICK_SEP) * col;
                int y = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * row;
                drawBrick(gc, x, y);
            }
        }
    }

    private void drawBrick(GraphicsContext gc, int x, int y) {
        gc.fillRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
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

    // TODO: pass bricks and the paddle, check all for collisions
    void checkCollision(final Shape ball, final Shape shape2) {
        ball.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> arg0, Bounds oldValue, Bounds newValue) {
                if (shape2.getBoundsInParent().intersects(newValue)) {
                    ball.setFill(Color.RED);
                    shape2.setFill(Color.RED);
                }
            }
        });
    }
}
