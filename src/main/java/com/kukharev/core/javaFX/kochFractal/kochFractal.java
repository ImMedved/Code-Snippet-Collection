/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*EN
0. — Main class starts the application.

1. — drawKochFractal(GraphicsContext gc, int level, double x1, double y1, double x5, double y5) — takes a graphics context, recursion level,
and coordinates of a line segment as input, calculates points for the Koch fractal, and draws it recursively.

2. — start(Stage primaryStage) — the main entry point for the JavaFX application. Sets up the primary stage and canvas, and initiates the drawing of the Koch fractal.
 */

/*RU
0. — Основной класс запускает приложение.

1. — drawKochFractal(GraphicsContext gc, int level, double x1, double y1, double x5, double y5) — принимает графический контекст, уровень рекурсии,
и координаты отрезка линии в качестве входных данных, вычисляет точки для фрактала Коха и рекурсивно рисует его.

2. — start(Stage PrimaryStage) — основная точка входа для приложения JavaFX. Устанавливает основную сцену и холст и начинает рисовать фрактал Коха.
 */

package com.kukharev.core.javaFX.kochFractal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class kochFractal extends Application {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final int INITIAL_LEVEL = 7; //Change the argument to change fractal level (Number of sides, angles and parts)

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Koch Fractal");

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);

        drawKochFractal(gc, INITIAL_LEVEL, 100, 300, 700, 300);

        Pane root = new Pane();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT));
        primaryStage.show();
    }

    public void drawKochFractal(GraphicsContext gc, int level, double x1, double y1, double x5, double y5) {
        if (level == 1) gc.strokeLine(x1, y1, x5, y5);
        else {
            double deltaX = x5 - x1;
            double deltaY = y5 - y1;

            double x2 = x1 + deltaX / 3;
            double y2 = y1 + deltaY / 3;

            double x3 = (x1 + x5) / 2 + Math.sqrt(3.0) / 6 * (y1 - y5);
            double y3 = (y1 + y5) / 2 + Math.sqrt(3.0) / 6 * (x5 - x1);

            double x4 = x1 + 2 * deltaX / 3;
            double y4 = y1 + 2 * deltaY / 3;

            drawKochFractal(gc, level - 1, x1, y1, x2, y2);
            drawKochFractal(gc, level - 1, x2, y2, x3, y3);
            drawKochFractal(gc, level - 1, x3, y3, x4, y4);
            drawKochFractal(gc, level - 1, x4, y4, x5, y5);
        }
    }
}

