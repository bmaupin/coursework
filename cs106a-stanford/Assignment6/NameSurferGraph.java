
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class NameSurferGraph extends Pane implements NameSurferConstants {
    public NameSurferGraph() {
        super();

        setWhiteBackground();
        drawDecadeLines();
    }

    private void setWhiteBackground() {
        this.setStyle("-fx-background-color: white");
    }

    private void drawDecadeLines() {
        for (int i = 0; i < NDECADES; i++) {
            drawVerticalLine(i);
        }
    }

    private void drawVerticalLine(int decadeIndex) {
        Line line = new Line();
        line.setStartY(0);
        line.endYProperty().bind(heightProperty());
        line.startXProperty().bind(widthProperty().divide(NDECADES).multiply(decadeIndex));
        line.endXProperty().bind(line.startXProperty());

        this.getChildren().add(line);
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
