/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*
The code creates a new file if the file is not in the selected path.
Next, random data is created and written to this file. Next, the data is output to the console.

Код создает новый файл, если файла нет в выбранном пути.
Далее создаются случайные данные и записываются в этот файл. Далее данные выводятся в консоль.
 */

package com.kukharev.core.JavaFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GameRecordManager {
    private static final int RECORD_SIZE = 23;
    private final String fileName;
    private final String top10FileName;

    public GameRecordManager(String fileName, String top10FileName) {
        this.fileName = fileName;
        this.top10FileName = top10FileName;
    }

    private String readString(RandomAccessFile file) throws IOException {
        byte[] bytes = new byte[15];
        file.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8).trim();
    }

    private void writeString(RandomAccessFile file, String str) throws IOException {
        byte[] bytes = new byte[15];
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(strBytes, 0, bytes, 0, Math.min(strBytes.length, 15));
        file.write(bytes);
    }

    public void newAttempt(String player) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(new File(fileName), "rw")) {
            long fileLength = file.length();
            file.seek(0);
            boolean found = false;
            while (file.getFilePointer() < fileLength) {
                String currentPlayer = readString(file);
                int bestScore = file.readInt();
                int attempts = file.readInt();

                if (currentPlayer.equals(player)) {
                    found = true;
                    file.seek(file.getFilePointer() - 4);
                    file.writeInt(attempts + 1);
                    break;
                }
            }

            if (!found) {
                file.seek(fileLength);
                writeString(file, player);
                file.writeInt(0);
                file.writeInt(1);
            }
        }
    }

    public void recordResult(String player, int result) throws IOException, ClassNotFoundException {
        try (RandomAccessFile file = new RandomAccessFile(new File(fileName), "rw")) {
            long fileLength = file.length();
            file.seek(0);
            while (file.getFilePointer() < fileLength) {
                String currentPlayer = readString(file);
                int bestScore = file.readInt();
                int attempts = file.readInt();

                if (currentPlayer.equals(player)) {
                    if (result > bestScore) {
                        file.seek(file.getFilePointer() - 8);
                        file.writeInt(result);
                    }
                    break;
                }
            }
        }

        updateTop10List(new PlayerRecord(player, result, 0));
    }

    private void updateTop10List(PlayerRecord newRecord) throws IOException, ClassNotFoundException {
        List<PlayerRecord> top10 = readTop10List();
        boolean updated = false;
        for (int i = 0; i < top10.size(); i++) {
            if (top10.get(i).getPlayerName().equals(newRecord.getPlayerName())) {
                if (newRecord.getBestScore() > top10.get(i).getBestScore()) {
                    top10.set(i, newRecord);
                    updated = true;
                }
                break;
            }
        }
        if (!updated && top10.size() < 10) top10.add(newRecord);
        top10.sort(Comparator.comparingInt(PlayerRecord::getBestScore).reversed());
        if (top10.size() > 10) top10.remove(top10.size() - 1);
        writeTop10List(top10);
    }

    private List<PlayerRecord> readTop10List() throws IOException, ClassNotFoundException {
        File file = new File(top10FileName);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<PlayerRecord>) ois.readObject();
        }
    }

    private void writeTop10List(List<PlayerRecord> top10) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(top10FileName))) {
            oos.writeObject(top10);
        }
    }

    public void showTop10() throws IOException, ClassNotFoundException {
        List<PlayerRecord> top10;
        top10 = readTop10List();

        System.out.println("Top 10 players 'The Game'");
        System.out.println("---------------------------------------");
        System.out.println("id<-->player<------->result<--->attempts");
        for (int i = 0; i < top10.size(); i++) {
            PlayerRecord record = top10.get(i);
            System.out.printf("%2d  %-10s %10d %10d%n", i + 1, record.getPlayerName(), record.getBestScore(), record.getAttempts());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Random random = new Random();
            int randomNumber1 = random.nextInt(200) + 1;
            int randomNumber2 = random.nextInt(200) + 1;
            GameRecordManager manager = new GameRecordManager("game_records.dat", "top10_records.dat");
            manager.newAttempt("PlayerOne");
            manager.recordResult("PlayerOne", randomNumber1);
            manager.newAttempt("PlayerTwo");
            manager.recordResult("PlayerTwo", randomNumber2);
            manager.showTop10();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PlayerRecord implements Serializable {
    private final String playerName;
    private final int bestScore;
    private final int attempts;

    public PlayerRecord(String playerName, int bestScore, int attempts) {
        this.playerName = playerName;
        this.bestScore = bestScore;
        this.attempts = attempts;
    }

    public String getPlayerName() {return playerName;}

    public int getBestScore() {return bestScore;}

    public int getAttempts() {return attempts;}
}
