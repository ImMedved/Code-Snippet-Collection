package com.kukharev.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseInserter {
    private static final String DB_URL = "jdbc:mysql://x.y.z.23:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    private static final int INSERT_INTERVAL = 20000; // 20 секунд
    private static final int DURATION = 200000; // 200 секунд
    private static final String[] RANDOM_COMMENTS = {"комментарий1", "комментарий2", "комментарий3", "комментарий4", "комментарий5"};

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask insertTask = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if (count < DURATION / INSERT_INTERVAL) {
                    insertRandomComment();
                    count++;
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(insertTask, 0, INSERT_INTERVAL);

        Thread countThread = new Thread(() -> {
            try {
                Thread.sleep(DURATION);
                int recordCount = countRecords();
                System.out.println("Всего записей добавлено: " + recordCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        countThread.start();

        try {
            countThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void insertRandomComment() {
        String randomComment = RANDOM_COMMENTS[new Random().nextInt(RANDOM_COMMENTS.length)];
        String sql = "INSERT INTO obisk(opomba) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, randomComment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int countRecords() {
        String sql = "SELECT COUNT(*) FROM obisk";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}

