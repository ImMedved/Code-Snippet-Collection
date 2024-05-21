/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*
BSTVisualizer: The main class of a JavaFX application that creates an interface with a text field and a button for adding nodes to a tree and renders the tree.
BSTVisualizer: Основной класс JavaFX приложения, который создает интерфейс с текстовым полем и кнопкой для добавления узлов в дерево и визуализирует дерево.

drawTree: A method that clears the panel and recursively draws the tree again after each node is added.
drawTree: Метод, который очищает панель и рекурсивно рисует дерево заново после добавления каждого узла.

drawTreeRec: A recursive method for drawing tree nodes and the lines connecting them, showing the hierarchy of the tree.
drawTreeRec: Рекурсивный метод для рисования узлов дерева и линий, соединяющих их, показывая иерархию дерева.
 */

package com.kukharev.animation.TreeSearch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BSTVisualizer extends Application {
    private BST bst = new BST();
    private Pane treePane = new Pane();

    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a value");
        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                bst.insert(value);
                inputField.clear();
                drawTree();
            } catch (NumberFormatException ex) {
                inputField.clear();
                inputField.setPromptText("Enter a valid integer");
            }
        });

        VBox vbox = new VBox(10, inputField, addButton, treePane);
        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setTitle("BST Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree() {
        treePane.getChildren().clear();
        drawTreeRec(treePane, bst.root, 400, 50, 200);
    }

    private void drawTreeRec(Pane pane, TreeNode node, double x, double y, double hGap) {
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
                drawTreeRec(pane, node.left, x - hGap, y + 50, hGap / 2);
            }

            if (node.right != null) {
                Line line = new Line(x + hGap, y + 50, x, y);
                pane.getChildren().add(line);
                drawTreeRec(pane, node.right, x + hGap, y + 50, hGap / 2);
            }
        }
    }
}