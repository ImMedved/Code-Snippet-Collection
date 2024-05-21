/*
Written by Alexander Kukharev
https://github.com/ImMedved
*/

/*EN
1. — start(Stage primaryStage) — Sets up the JavaFX application window with a canvas and initializes the drawing process.

2. — drawTree(GraphicsContext gc, double x1, double y1, double x2, double y2) — Recursively draws the fractal tree branches.
 */

/*RU
1. — start(Stage PrimaryStage) — настраивает окно приложения JavaFX с холстом и инициализирует процесс рисования.

2. — drawTree(GraphicsContext gc, double x1, double y1, double x2, double y2) — рекурсивно рисует ветви фрактального дерева.
*/
package com.kukharev.javaFX.FractalTree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Advise: don't use FRACTION bigger, than 1/1.3
public class FractalTree extends Application {

    private static final double ANGLE = Math.toRadians(50); // Angle in radians
    private static final double FRACTION = 1.0 / 1.7; // Height share for new branch
    private static final double MIN_LENGTH = 2.0; // Minimum branch length

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawTree(gc, 400, 800, 400, 600); // Starting position and barrel length

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Fractal Tree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Function for drawing tree branches
    private void drawTree(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        gc.strokeLine(x1, y1, x2, y2);

        double length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        if (length < MIN_LENGTH) {
            return;
        }

        double newLength = length * FRACTION;
        double angle = Math.atan2(y2 - y1, x2 - x1);

        double x3 = x2 + newLength * Math.cos(angle + ANGLE);
        double y3 = y2 + newLength * Math.sin(angle + ANGLE);

        double x4 = x2 + newLength * Math.cos(angle - ANGLE);
        double y4 = y2 + newLength * Math.sin(angle - ANGLE);

        drawTree(gc, x2, y2, x3, y3);
        drawTree(gc, x2, y2, x4, y4);
    }
}

