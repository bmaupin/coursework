/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Pyramid extends Application {

/** Width of each brick in pixels */
    private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
    private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
    private static final int BRICKS_IN_BASE = 14;
    
    private static final double CANVAS_WIDTH = BRICKS_IN_BASE * BRICK_WIDTH * 1.5;
    private static final double CANVAS_HEIGHT = BRICKS_IN_BASE * BRICK_HEIGHT * 1.5;
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        drawPyramid(gc);
        
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void drawPyramid(GraphicsContext gc) {
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            drawRow(gc, i);
        }
    }
    
    private void drawRow(GraphicsContext gc, int row) {
        int bricksInRow = BRICKS_IN_BASE - row;
        double y = CANVAS_HEIGHT - BRICK_HEIGHT * (row + 1);
        
        for (int i = 0; i < bricksInRow; i++) {
            double x = (CANVAS_WIDTH - BRICK_WIDTH * bricksInRow) / 2 + BRICK_WIDTH * i;
            
            drawBrick(gc, x, y);
        }
    }
    
    private void drawBrick(GraphicsContext gc, double x, double y) {
        gc.strokeRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }
}

