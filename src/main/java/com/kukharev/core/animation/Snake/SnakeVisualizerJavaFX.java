package com.kukharev.core.animation.Snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SnakeVisualizerJavaFX extends Application {
    private static final int RECT_SIZE = 20;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private Snake snake;

    @Override
    public void start(Stage primaryStage) {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Snake Visualization - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        visualize(pane);

        runSequence(pane);
    }

    private void visualize(Pane pane) {
        pane.getChildren().clear();
        for (int[] part : snake.getBody()) {
            Rectangle rect = new Rectangle(part[0], part[1], RECT_SIZE, RECT_SIZE);
            rect.setFill(Color.GREEN);
            pane.getChildren().add(rect);
        }
    }

    private void runSequence(Pane pane) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                snake.moveForward(3);
                visualize(pane);
                Thread.sleep(1000);
                snake.turnLeft();
                snake.moveForward(2);
                visualize(pane);
                Thread.sleep(1000);
                snake.turnRight();
                snake.moveForward(2);
                visualize(pane);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
