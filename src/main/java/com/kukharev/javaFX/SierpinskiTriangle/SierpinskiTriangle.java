/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*EN
1. — start(Stage primaryStage) — Sets up the JavaFX application window with a canvas and initializes the drawing process.

2. — drawSierpinski(GraphicsContext gc, double[] xPoints, double[] yPoints) — Recursively draws the Sierpinski triangle.
Graphics context gc, arrays of x-coordinates xPoints and y-coordinates yPoints of the triangle vertices. Checks if any side is below the minimum threshold
and draws the current triangle. Calculates the midpoints of the sides, recursively calls itself to draw the four smaller triangles.
 */

/*RU
1. — start(Stage PrimaryStage) — настраивает окно приложения JavaFX с холстом и инициализирует процесс рисования.

2. — drawSierpinski(GraphicsContext gc, double[] xPoints, double[] yPoints) — Рекурсивно рисует треугольник Серпинского.
Графический контекст gc, массивы координат x xPoints и y-координат yPoints вершин треугольника. Проверяет, находится ли какая-либо сторона ниже минимального порога.
и рисует текущий треугольник. Вычисляет середины сторон и рекурсивно вызывает себя для рисования четырех меньших треугольников.
*/

package com.kukharev.javaFX.SierpinskiTriangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SierpinskiTriangle extends Application {

    private static final double MIN_SIDE_LENGTH = 5.0; // Minimum side length

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double[] xPoints = {400, 50, 750};
        double[] yPoints = {50, 750, 750};

        drawSierpinski(gc, xPoints, yPoints);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Sierpinski Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Function for drawing the Sierpinski triangle
    private void drawSierpinski(GraphicsContext gc, double[] xPoints, double[] yPoints) {
        // Calculating the lengths of the sides of a triangle
        double side1 = Math.sqrt(Math.pow(xPoints[1] - xPoints[0], 2) + Math.pow(yPoints[1] - yPoints[0], 2));
        double side2 = Math.sqrt(Math.pow(xPoints[2] - xPoints[1], 2) + Math.pow(yPoints[2] - yPoints[1], 2));
        double side3 = Math.sqrt(Math.pow(xPoints[2] - xPoints[0], 2) + Math.pow(yPoints[2] - yPoints[0], 2));

        // Checking the minimum side length
        if (side1 < MIN_SIDE_LENGTH || side2 < MIN_SIDE_LENGTH || side3 < MIN_SIDE_LENGTH) return;

        // Drawing a triangle
        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xPoints, yPoints, 3);

        // Calculating the midpoints of the sides of a triangle
        double[] midX = {
                (xPoints[0] + xPoints[1]) / 2,
                (xPoints[1] + xPoints[2]) / 2,
                (xPoints[2] + xPoints[0]) / 2
        };
        double[] midY = {
                (yPoints[0] + yPoints[1]) / 2,
                (yPoints[1] + yPoints[2]) / 2,
                (yPoints[2] + yPoints[0]) / 2
        };

        // Recursive call for each of the 4 new triangles
        drawSierpinski(gc, new double[]{xPoints[0], midX[0], midX[2]}, new double[]{yPoints[0], midY[0], midY[2]});
        drawSierpinski(gc, new double[]{midX[0], xPoints[1], midX[1]}, new double[]{midY[0], yPoints[1], midY[1]});
        drawSierpinski(gc, new double[]{midX[2], midX[1], xPoints[2]}, new double[]{midY[2], midY[1], yPoints[2]});
        drawSierpinski(gc, new double[]{midX[0], midX[1], midX[2]}, new double[]{midY[0], midY[1], midY[2]});
    }
}

