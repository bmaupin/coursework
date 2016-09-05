/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ProgramHierarchy extends Application {
    private static final int BOX_WIDTH = 150;
    private static final int BOX_HEIGHT = 50;
	
    private static final double CANVAS_WIDTH = BOX_WIDTH * 3 + BOX_HEIGHT * 4;
    private static final double CANVAS_HEIGHT = BOX_HEIGHT * 5;
	
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        drawHierarchy(gc);
        
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void drawHierarchy(GraphicsContext gc) {
    	drawProgramBox(gc);
    	drawConsoleProgramBox(gc);
    	drawGraphicsProgramBox(gc);
    	drawDialogProgramBox(gc);
    }
    
    private static void drawProgramBox(GraphicsContext gc) {
    	double x = CANVAS_WIDTH / 2 - BOX_WIDTH / 2;
    	double y = BOX_HEIGHT;
    	
    	drawBoxWithText(gc, "Program", x, y);
    }
    
    private static void drawGraphicsProgramBox(GraphicsContext gc) {
    	double x = CANVAS_WIDTH / 2 - BOX_WIDTH / 2 - BOX_HEIGHT - BOX_WIDTH;
    	double y = BOX_HEIGHT * 3;
    	
    	drawBoxWithText(gc, "GraphicsProgram", x, y);
    	drawBoxLine(gc, x, y);
    }
    
    private static void drawConsoleProgramBox(GraphicsContext gc) {
    	double x = CANVAS_WIDTH / 2 - BOX_WIDTH / 2;
    	double y = BOX_HEIGHT * 3;
    	
    	drawBoxWithText(gc, "ConsoleProgram", x, y);
    	drawBoxLine(gc, x, y);
    }
    
    private static void drawDialogProgramBox(GraphicsContext gc) {
    	double x = CANVAS_WIDTH / 2 - BOX_WIDTH / 2 + BOX_HEIGHT + BOX_WIDTH;
    	double y = BOX_HEIGHT * 3;
    	
    	drawBoxWithText(gc, "DialogProgram", x, y);
    	drawBoxLine(gc, x, y);
    }
    
    private static void drawBoxWithText(GraphicsContext gc, String text, double x, double y) {
    	gc.strokeRect(x, y, BOX_WIDTH, BOX_HEIGHT);
    	
    	double textX = x + BOX_WIDTH / 2;
    	double textY = y + BOX_HEIGHT / 2;
    	
    	drawCenteredText(gc, text, textX, textY);
    }
    
    private static void drawCenteredText(GraphicsContext gc, String text, double x, double y) {
    	// Center text horizontally on x
        gc.setTextAlign(TextAlignment.CENTER);
    	// Centers text vertically on y
        gc.setTextBaseline(VPos.CENTER);
    	gc.fillText(text, x, y);
    }
    
    private static void drawBoxLine(GraphicsContext gc, double boxX, double boxY) {
    	gc.strokeLine(CANVAS_WIDTH / 2, boxY - BOX_HEIGHT, boxX + BOX_WIDTH / 2, boxY);
    }
}

