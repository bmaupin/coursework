
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class NameSurferGraph extends Pane implements NameSurferConstants {
    private static final int LABEL_PADDING = 2;
    private int graphColorIndex = 0;
    // Colors from
    // https://material.google.com/style/color.html#color-color-palette
    private int[][] graphColors = new int[][] {
            { 63, 81, 181 },
            { 0, 150, 136 },
            { 255, 235, 59 },
            { 233, 30, 99 },
            { 33, 150, 243 },
            { 76, 175, 80 },
            { 255, 193, 7 },
            { 244, 67, 54 },
            { 121, 85, 72 },
            { 3, 169, 244 },
            { 139, 195, 74 },
            { 255, 152, 0 },
            { 103, 58, 183 },
            { 0, 188, 212 },
            { 205, 220, 57 },
            { 255, 87, 34 },
            { 156, 39, 176 },
            { 158, 158, 158 },
            { 96, 125, 139 },
            { 0, 0, 0 }
    };

    public NameSurferGraph() {
        super();

        setWhiteBackground();
        drawVerticalGridLines();
        drawHorizontalGridLines();
        drawDecadeLabels();
    }

    private void setWhiteBackground() {
        this.setStyle("-fx-background-color: white");
    }

    private void drawVerticalGridLines() {
        for (int i = 0; i < NDECADES; i++) {
            drawVerticalLine(i);
        }
    }

    private void drawVerticalLine(int decadeIndex) {
        Line line = new Line();
        line.startXProperty().bind(getDecadeStartX(decadeIndex));
        line.endXProperty().bind(line.startXProperty());
        line.setStartY(0);
        line.endYProperty().bind(heightProperty());

        this.getChildren().add(line);
    }

    private void drawHorizontalGridLines() {
        drawTopLine();
        drawBottomLine();
    }

    private void drawTopLine() {
        Line line = new Line();
        line.setStartX(0);
        line.endXProperty().bind(widthProperty());
        line.setStartY(GRAPH_MARGIN_SIZE);
        line.setEndY(GRAPH_MARGIN_SIZE);

        this.getChildren().add(line);
    }

    private void drawBottomLine() {
        Line line = new Line();
        line.setStartX(0);
        line.endXProperty().bind(widthProperty());
        line.startYProperty().bind(getBottomMarginY());
        line.endYProperty().bind(line.startYProperty());

        this.getChildren().add(line);
    }

    private void drawDecadeLabels() {
        for (int i = 0; i < NDECADES; i++) {
            drawDecadeLabel(i);
        }
    }

    private void drawDecadeLabel(int decadeIndex) {
        Label label = new Label(Integer.toString(START_DECADE + decadeIndex * 10));
        label.layoutXProperty().bind(getDecadeStartX(decadeIndex).add(LABEL_PADDING));
        label.layoutYProperty().bind(getBottomMarginY().add(LABEL_PADDING));

        this.getChildren().add(label);
    }

    public void addEntry(NameSurferEntry entry) {
        Color color = getNextColor();

        for (int i = 0; i < NDECADES - 1; i++) {
            drawDecadeGraphLine(i, entry.getRank(i), entry.getRank(i + 1), color);
        }
    }

    private void drawDecadeGraphLine(int decadeIndex, int startRank, int endRank, Color color) {
        Line line = new Line();

        if (startRank == 0) {
            startRank = MAX_RANK;
        }
        if (endRank == 0) {
            endRank = MAX_RANK;
        }

        line.startXProperty().bind(getDecadeStartX(decadeIndex));
        line.endXProperty().bind(getDecadeStartX(decadeIndex + 1));
        line.startYProperty().bind(getRankY(startRank));
        line.endYProperty().bind(getRankY(endRank));
        line.setStroke(color);

        this.getChildren().add(line);
    }

    private Color getNextColor() {
        Color nextColor = Color.rgb(
                graphColors[graphColorIndex][0],
                graphColors[graphColorIndex][1],
                graphColors[graphColorIndex][2]);

        graphColorIndex++;
        if (graphColorIndex >= graphColors.length) {
            graphColorIndex = 0;
        }

        return nextColor;
    }

    private DoubleBinding getDecadeStartX(int decadeIndex) {
        return widthProperty().divide(NDECADES).multiply(decadeIndex);
    }

    private DoubleBinding getBottomMarginY() {
        return heightProperty().subtract(GRAPH_MARGIN_SIZE);
    }

    private DoubleBinding getRankY(int rank) {
        return heightProperty().subtract(GRAPH_MARGIN_SIZE * 2)
                .divide(MAX_RANK)
                .multiply(rank)
                .add(GRAPH_MARGIN_SIZE);
    }
}

// public class NameSurferGraph extends GCanvas
// implements NameSurferConstants, ComponentListener {
//
// /**
// * Creates a new NameSurferGraph object that displays the data.
// */
// public NameSurferGraph() {
// addComponentListener(this);
// // You fill in the rest //
// }
//
// /**
// * Clears the list of name surfer entries stored inside this class.
// */
// public void clear() {
// // You fill this in //
// }
//
// /* Method: addEntry(entry) */
// /**
// * Adds a new NameSurferEntry to the list of entries on the display.
// * Note that this method does not actually draw the graph, but
// * simply stores the entry; the graph is drawn by calling update.
// */
// public void addEntry(NameSurferEntry entry) {
// // You fill this in //
// }
//
//
//
// /**
// * Updates the display image by deleting all the graphical objects
// * from the canvas and then reassembling the display according to
// * the list of entries. Your application must call update after
// * calling either clear or addEntry; update is also called whenever
// * the size of the canvas changes.
// */
// public void update() {
// // You fill this in //
// }
//
//
//
//
// /* Implementation of the ComponentListener interface */
// public void componentHidden(ComponentEvent e) { }
// public void componentMoved(ComponentEvent e) { }
// public void componentResized(ComponentEvent e) { update(); }
// public void componentShown(ComponentEvent e) { }
// }
