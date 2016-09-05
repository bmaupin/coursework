/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Target extends Application {
    private static final int CANVAS_HEIGHT = 200;
    private static final int CANVAS_WIDTH = 300;
    private static final int PIXELS_PER_INCH = 72;
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        drawTarget(gc);
        
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void drawTarget(GraphicsContext gc) {
        drawOuterRedCircle(gc);
        drawWhiteCircle(gc);
        drawInnerRedCircle(gc);
    }
    
    private void drawOuterRedCircle(GraphicsContext gc) {
        drawCenteredCircle(gc, Color.RED, 1);
    }
    
    private void drawWhiteCircle(GraphicsContext gc) {
        drawCenteredCircle(gc, Color.WHITE, 0.65);
    }
    
    private void drawInnerRedCircle(GraphicsContext gc) {
        drawCenteredCircle(gc, Color.RED, 0.3);
    }
    
    private void drawCenteredCircle(GraphicsContext gc, Color color, double radiusInInches) {
        gc.setFill(color);
        
        double circleHeight = radiusInInches * PIXELS_PER_INCH * 2;
        double circleWidth = circleHeight;
        double x = CANVAS_WIDTH / 2 - circleWidth / 2;
        double y = CANVAS_HEIGHT / 2 - circleHeight / 2;
        
        gc.fillOval(x, y, circleWidth, circleHeight);
    }
}
