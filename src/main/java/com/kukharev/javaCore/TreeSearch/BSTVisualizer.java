/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*
Implement a program to visualize the structure of a binary search tree (BST) or ternary search tree (TST) in JavaFX
that will draw the tree using circles for nodes and lines for connections between them.
The rendering will occur from top to bottom, with the root of the tree located above the subtrees.

Реализовать программу для визуализации структуры бинарного дерева поиска (BST) или тернарного дерева поиска (TST) в JavaFX,
которая будет рисовать дерево с помощью кружков для узлов и линий для связи между ними. Визуализация будет происходить сверху вниз,
с корнем дерева, расположенным над поддеревьями.
 */

/*
The main JavaFX application class that creates the scene and renders the tree.
Основной класс JavaFX приложения, который создает сцену и визуализирует дерево.

drawTree: Recursive method for drawing tree nodes and the lines connecting them.
drawTree: Рекурсивный метод для рисования узлов дерева и соединяющих их линий.
 */

package com.kukharev.javaCore.TreeSearch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BSTVisualizer extends Application {
    private BST bst = new BST();

    @Override
    public void start(Stage primaryStage) {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        Pane pane = new Pane();
        drawTree(pane, bst.root, 400, 50, 200);

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("BST Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(Pane pane, TreeNode node, double x, double y, double hGap) {
        if (node != null) {
            Circle circle = new Circle(x, y, 15);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);
            Text text = new Text(x - 5, y + 5, String.valueOf(node.value));
            pane.getChildren().add(text);

            if (node.left != null) {
                Line line = new Line(x - hGap, y + 50, x, y);
                pane.getChildren().add(line);
                drawTree(pane, node.left, x - hGap, y + 50, hGap / 2);
            }

            if (node.right != null) {
                Line line = new Line(x + hGap, y + 50, x, y);
                pane.getChildren().add(line);
                drawTree(pane, node.right, x + hGap, y + 50, hGap / 2);
            }
        }
    }
}
