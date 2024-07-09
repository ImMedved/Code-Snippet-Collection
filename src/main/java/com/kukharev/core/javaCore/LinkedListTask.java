/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*
Task/Задача
Task a: Create a LinkedList of 7 points with the same y-coordinate and consecutive x-coordinates starting from 5.
Perform a circular shift of the elements by removing the first element and appending it to the end, then print the list.
Repeat this process until all elements have been shifted.

Task b: Create a LinkedList of 7 points with the same y-coordinate and consecutive x-coordinates starting from 5.
Perform a circular shift by removing the first element and adding a new element with the y-coordinate of the last element,
x-coordinate incremented by 1, and color red, then print the list. Repeat this process until the list completes a full cycle.

Задача а: Создайте LinkedList из 7 точек с одинаковой y-координатой и последовательными x-координатами, начиная с 5.
Выполните циклический сдвиг элементов, удаляя первый элемент и добавляя его в конец списка, затем выведите список.
Повторяйте процесс, пока все элементы не будут сдвинуты.

Задача б: Создайте LinkedList из 7 точек с одинаковой y-координатой и последовательными x-координатами, начиная с 5.
Выполните циклический сдвиг, удаляя первый элемент и добавляя новый элемент с y-координатой последнего элемента, x-координатой, увеличенной на 1,
и красным цветом, затем выведите список. Повторяйте процесс, пока список не завершит полный цикл.
 */

package com.kukharev.core.javaCore;

import java.awt.Color;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class LinkedListTask {
    public static void main(String[] args) {
        LinkedList<RPoint> ll = new LinkedList<>();

        // Create 7 points with the same y-coordinate and x-coordinates from 5 to 11, color - yellow
        for (int i = 0; i < 7; i++) ll.add(new RPoint(5 + i, 10, Color.YELLOW));

        // Task a
        System.out.println("Task a:");
        IntStream.range(0, ll.size()).mapToObj(i -> ll.removeFirst()).forEach(first -> {
            ll.addLast(first);
            System.out.println(ll);
        });

        // Task b
        System.out.println("Task b:");
        IntStream.range(0, ll.size()).mapToObj(i -> ll.removeFirst()).map(first -> ll.getLast()).forEach(last -> {
            ll.addLast(new RPoint(last.x() + 1, last.y(), Color.RED));
            System.out.println(ll);
        });
    }
}

// Java 17+ record for RTocka
record RPoint(int x, int y, Color color) {}