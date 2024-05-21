/*
Written by Alexander Kukharev
https://github.com/ImMedved
*/

/*EN
SegmentDisplay.java
start(Stage primaryStage) — The method initializes and runs the JavaFX application. Creates a Pane where interface elements will be displayed.
Initializes a Segment object and adds it to the panel. Creates Start Up, Start Down and Stop buttons and sets event handlers for them.
Creates a scene and installs it in the main window (primaryStage).

startCountingUp() — The method starts a counter that increments the value on the 7-segment display every second.
Stops the previous animation if it was running. Creates a Timeline that increments count every second.
Sets the animation loop to infinite (Timeline.INDEFINITE) and starts the animation.

startCountingDown() — The method starts a counter that decreases the value on the 7-segment display every second. Reverse method for startCountingUp.

stopCounting() — The method stops the current counter. Stops Timeline if it is running.

Segment.java
Segment() — Constructor for a class that initializes a 7-segment display. Creates seven polygons representing each display segment.
Defines the vertex coordinates for each polygon. Sets the color of the segments to gray (Color.GRAY). Adds segments to the Pane.

createSegment(double... points) — The method creates a polygon (segment) with specified coordinates. Returns a new Polygon object with the given vertex coordinates.

setNumber(int number) — The method updates the state of the segments to reflect the given number. Determines which segments should be turned on to display each number from 0 to 9.
Sets the color of the segments to red (Color.RED) if the segment should be turned on, or to gray (Color.GRAY) if it should be turned off.
*/

/*RU
SegmentDisplay.java
start(Stage primaryStage) — Метод инициализирует и запускает JavaFX приложение. Создает Pane (панель), где будут отображаться элементы интерфейса.
Инициализирует объект Segment и добавляет его на панель. Создает кнопки Start Up, Start Down и Stop, устанавливает для них обработчики событий.
Создает сцену и устанавливает её в основное окно (primaryStage).

startCountingUp() — Метод запускает счетчик, который увеличивает значение на 7-сегментном дисплее каждую секунду.
Останавливает предыдущую анимацию, если она была запущена. Создает Timeline, который каждую секунду увеличивает count.
Устанавливает цикл анимации на бесконечный (Timeline.INDEFINITE) и запускает анимацию.

startCountingDown() — Метод запускает счетчик, который уменьшает значение на 7-сегментном дисплее каждую секунду. Обратный метод для startCountingUp.

stopCounting() — Метод останавливает текущий счетчик. Останавливает Timeline, если она запущена.

Segment.java
Segment() — Конструктор класса, который инициализирует 7-сегментный дисплей. Создает семь полигонов, представляющих каждый сегмент дисплея.
Определяет координаты вершин для каждого полигона. Устанавливает цвет сегментов в серый (Color.GRAY). Добавляет сегменты на панель (Pane).

createSegment(double... points) — Метод создает полигон (сегмент) с заданными координатами. Возвращает новый объект Polygon с заданными координатами вершин.

setNumber(int number) — Метод обновляет состояние сегментов для отображения заданного числа.
Определяет, какие сегменты должны быть включены для отображения каждого числа от 0 до 9. Устанавливает цвет сегментов в красный (Color.RED),
если сегмент должен быть включен, или в серый (Color.GRAY), если он должен быть выключен.
*/

package com.kukharev.animation.SevenSegmentDisplay;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SegmentDisplay extends Application {

    private Segment segment;
    private int count = 0;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        segment = new Segment();
        segment.setLayoutX(50);
        segment.setLayoutY(50);
        root.getChildren().add(segment);

        VBox controls = new VBox(10);
        controls.setLayoutX(50);
        controls.setLayoutY(150);

        Button startUp = new Button("Start Up");
        startUp.setOnAction(e -> startCountingUp());

        Button startDown = new Button("Start Down");
        startDown.setOnAction(e -> startCountingDown());

        Button stop = new Button("Stop");
        stop.setOnAction(e -> stopCounting());

        controls.getChildren().addAll(startUp, startDown, stop);
        root.getChildren().add(controls);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("7-Segment Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startCountingUp() {
        if (timeline != null) {
            timeline.stop();
        }
        count = 0;
        segment.setNumber(count);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            count = (count + 1) % 10;
            segment.setNumber(count);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void startCountingDown() {
        if (timeline != null) {
            timeline.stop();
        }
        count = 9;
        segment.setNumber(count);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            count = (count - 1 + 10) % 10;
            segment.setNumber(count);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopCounting() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}

class Segment extends Pane {
    private Polygon[] segments = new Polygon[7];
    private static final double WIDTH = 40;
    private static final double HEIGHT = 80;
    private static final double SEGMENT_WIDTH = 10;

    public Segment() {
        // Define the segments
        segments[0] = createSegment(0, 0, WIDTH, 0, WIDTH - SEGMENT_WIDTH, SEGMENT_WIDTH, SEGMENT_WIDTH, SEGMENT_WIDTH);
        segments[1] = createSegment(WIDTH, 0, WIDTH, HEIGHT / 2, WIDTH - SEGMENT_WIDTH, HEIGHT / 2 - SEGMENT_WIDTH, WIDTH - SEGMENT_WIDTH, SEGMENT_WIDTH);
        segments[2] = createSegment(WIDTH, HEIGHT / 2, WIDTH, HEIGHT, WIDTH - SEGMENT_WIDTH, HEIGHT - SEGMENT_WIDTH, WIDTH - SEGMENT_WIDTH, HEIGHT / 2 + SEGMENT_WIDTH);
        segments[3] = createSegment(0, HEIGHT, WIDTH, HEIGHT, WIDTH - SEGMENT_WIDTH, HEIGHT - SEGMENT_WIDTH, SEGMENT_WIDTH, HEIGHT - SEGMENT_WIDTH);
        segments[4] = createSegment(0, HEIGHT / 2, SEGMENT_WIDTH, HEIGHT / 2 + SEGMENT_WIDTH, SEGMENT_WIDTH, HEIGHT - SEGMENT_WIDTH, 0, HEIGHT);
        segments[5] = createSegment(0, 0, SEGMENT_WIDTH, SEGMENT_WIDTH, SEGMENT_WIDTH, HEIGHT / 2 - SEGMENT_WIDTH, 0, HEIGHT / 2);
        segments[6] = createSegment(SEGMENT_WIDTH, HEIGHT / 2 - SEGMENT_WIDTH / 2, WIDTH - SEGMENT_WIDTH, HEIGHT / 2 - SEGMENT_WIDTH / 2, WIDTH - SEGMENT_WIDTH, HEIGHT / 2 + SEGMENT_WIDTH / 2, SEGMENT_WIDTH, HEIGHT / 2 + SEGMENT_WIDTH / 2);

        // Add segments to the pane
        for (Polygon segment : segments) {
            segment.setFill(Color.GRAY);
            getChildren().add(segment);
        }
    }

    private Polygon createSegment(double... points) {
        return new Polygon(points);
    }

    public void setNumber(int number) {
        boolean[][] segmentStates = {
                {true, true, true, true, true, true, false},        // 0
                {false, true, true, false, false, false, false},    // 1
                {true, true, false, true, true, false, true},       // 2
                {true, true, true, true, false, false, true},       // 3
                {false, true, true, false, false, true, true},      // 4
                {true, false, true, true, false, true, true},       // 5
                {true, false, true, true, true, true, true},        // 6
                {true, true, true, false, false, false, false},     // 7
                {true, true, true, true, true, true, true},         // 8
                {true, true, true, true, false, true, true}         // 9
        };

        for (int i = 0; i < segments.length; i++) {
            segments[i].setFill(segmentStates[number][i] ? Color.RED : Color.GRAY);
        }
    }
}
