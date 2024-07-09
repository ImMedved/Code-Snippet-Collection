package com.kukharev.core.animation.Snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SnakeVisualizerAWT extends JFrame {
    private static final int RECT_SIZE = 20;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private final Snake snake;
    private final JPanel panel;

    public SnakeVisualizerAWT() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                visualize(g);
            }
        };
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);

        setTitle("Snake Visualization - AWT");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SnakeVisualizerAWT frame = new SnakeVisualizerAWT();
            frame.setVisible(true);
            frame.runSequence();
        });
    }

    private void visualize(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        for (int[] part : snake.getBody()) {
            g.setColor(Color.GREEN);
            g.fillRect(part[0], part[1], RECT_SIZE, RECT_SIZE);
        }
    }

    private void runSequence() {
        new Timer(1000, new ActionListener() {
            private int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (step) {
                    case 0 -> snake.moveForward(3);
                    case 1 -> {
                        snake.turnLeft();
                        snake.moveForward(2);
                    }
                    case 2 -> {
                        snake.turnRight();
                        snake.moveForward(2);
                    }
                    default -> ((Timer) e.getSource()).stop();
                }
                panel.repaint();
                step++;
            }
        }).start();
    }
}
