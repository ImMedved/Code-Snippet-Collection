/*
writeRecords: Writes a list of records to a file.
readRecords: Reads a list of records from a file.
appendRecords: Appends new records to the end of the file.
removeRecord: Removes a record by index.
updateRecord: Updates a record by index.
printRecords: Prints all records to the console.
countRecords: Returns the number of records.

writeRecords: Записывает список записей в файл.
readRecords: Читает список записей из файла.
appendRecords: Добавляет новые записи в конец файла.
removeRecord: Удаляет запись по индексу.
updateRecord: Обновляет запись по индексу.
printRecords: Выводит все записи в консоль.
countRecords: Возвращает количество записей.
 */

package com.kukharev.CRUD;

import java.io.*;
import java.util.List;

public class RecordManager {
    private static final String FILE_NAME = "records.dat";

    public static void writeRecords(List<Record> records) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(records);
        }
    }

    public static List<Record> readRecords() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Record>) ois.readObject();
        }
    }

    public static void appendRecords(List<Record> newRecords) throws IOException, ClassNotFoundException {
        List<Record> records = readRecords();
        records.addAll(newRecords);
        writeRecords(records);
    }

    public static void removeRecord(int index) throws IOException, ClassNotFoundException {
        List<Record> records = readRecords();
        if (index >= 0 && index < records.size()) {
            records.remove(index);
            writeRecords(records);
        }
    }

    public static void updateRecord(int index, Record newRecord) throws IOException, ClassNotFoundException {
        List<Record> records = readRecords();
        if (index >= 0 && index < records.size()) {
            records.set(index, newRecord);
            writeRecords(records);
        }
    }

    public static void printRecords() throws IOException, ClassNotFoundException {
        List<Record> records = readRecords();
        records.forEach(System.out::println);
    }

    public static int countRecords() throws IOException, ClassNotFoundException {
        return readRecords().size();
    }
}

