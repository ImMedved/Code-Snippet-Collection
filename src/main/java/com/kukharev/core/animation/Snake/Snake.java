package com.kukharev.core.animation.Snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<int[]> body;
    private Direction direction;

    public Snake(int startX, int startY) {
        body = new ArrayList<>();
        body.add(new int[]{startX, startY});
        direction = Direction.RIGHT;
    }

    public List<int[]> getBody() {return body;}

    public Direction getDirection() {return direction;}

    public void turnLeft() {
        switch (direction) {
            case UP -> direction = Direction.LEFT;
            case DOWN -> direction = Direction.RIGHT;
            case LEFT -> direction = Direction.DOWN;
            case RIGHT -> direction = Direction.UP;
        }
    }

    public void turnRight() {
        switch (direction) {
            case UP -> direction = Direction.RIGHT;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
            case RIGHT -> direction = Direction.DOWN;
        }
    }

    public void moveForward(int steps) {
        int[] head = body.get(0);
        int newX = head[0];
        int newY = head[1];
        switch (direction) {
            case UP -> newY -= steps;
            case DOWN -> newY += steps;
            case LEFT -> newX -= steps;
            case RIGHT -> newX += steps;
        }
        body.add(0, new int[]{newX, newY});
        while (body.size() > steps + 1) body.remove(body.size() - 1);
    }
}