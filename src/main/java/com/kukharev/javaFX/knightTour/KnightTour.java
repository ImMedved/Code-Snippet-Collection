/*
Written by Alexander Kukharev
https://github.com/ImMedved
The code is not complete and does not work correctly
*/

/*EN
1. — solveKnightTour(int x, int y, int moveCount) — receives the starting coordinates of the knight and the current move count.
It attempts to solve the Knight's Tour problem recursively, marking the board and updating the path.

2. — getNextMoves(int x, int y) — receives the current coordinates of the knight and calculates all possible next moves.

3. — isValidMove(int x, int y) — checks if a move to the given coordinates is valid (within bounds and not yet visited).

4. — drawBoard(GraphicsContext gc) — draws the chessboard and the path of the knight.

5. — start(Stage primaryStage) — sets up the JavaFX application, initializes the board, and starts the Knight's Tour algorithm.
 */

/*RU
1. —solveKnightTour(int x, int y, int moveCount) — получает начальные координаты коня и текущий счетчик ходов.
Он пытается решить проблему "Ход конем" рекурсивно, отмечая доску и обновляя путь.

2. — getNextMoves(int x, int y) — получает текущие координаты коня и рассчитывает все возможные следующие ходы.

3. — isValidMove(int x, int y) — проверяет, допустимо ли перемещение по заданным координатам (в пределах границ и еще не посещено).

4. — drawBoard(GraphicsContext gc) — рисует шахматную доску и путь коня.

5. — start(Stage PrimaryStage) — настраивает приложение JavaFX, инициализирует плату и запускает алгоритм Knight's Tour.
 */

package com.kukharev.javaFX.knightTour;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KnightTour extends Application {

    private static final int SIZE = 8; // Chessboard size
    private static final int CELL_SIZE = 100; // Cell size
    private int[][] board = new int[SIZE][SIZE]; // Chessboard
    private List<int[]> path = new ArrayList<>(); // The way of the knight

    private static final int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(SIZE * CELL_SIZE, SIZE * CELL_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Knight's Tour");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial position of the knight
        int startX = 0;
        int startY = 0;

        if (solveKnightTour(startX, startY, 1)) drawBoard(gc);
        else System.out.println("No solution found.");
    }

    // Recursive function for pathfinding
    private boolean solveKnightTour(int x, int y, int moveCount) {
        board[x][y] = moveCount;
        path.add(new int[]{x, y});

        if (moveCount == SIZE * SIZE) return true;

        List<int[]> nextMoves = getNextMoves(x, y);
        for (Iterator<int[]> iterator = nextMoves.iterator(); iterator.hasNext(); ) {
            int[] move = iterator.next();
            int nextX = move[0];
            int nextY = move[1];
            if (isValidMove(nextX, nextY)) if (solveKnightTour(nextX, nextY, moveCount + 1)) return true;
        }

        // Cancel move
        board[x][y] = 0;
        path.remove(path.size() - 1);
        return false;
    }

    // Getting possible next moves
    private List<int[]> getNextMoves(int x, int y) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isValidMove(nextX, nextY)) moves.add(new int[]{nextX, nextY});
        }
        return moves;
    }

    // Checking the validity of the move
    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && board[x][y] == 0;
    }

    // Drawing the board and the knight's path
    private void drawBoard(GraphicsContext gc) {
        for (int i = 0; i < SIZE; i++)
            for (int j = SIZE - 1; j >= 0; j--) {
                gc.setFill((i + j) % 2 == 0 ? Color.BEIGE : Color.BROWN);
                gc.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

        gc.setFill(Color.RED);
        path.forEach(move -> gc.fillOval(move[1] * CELL_SIZE + CELL_SIZE / 4, move[0] * CELL_SIZE + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2));
    }
}

