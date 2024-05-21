package com.kukharev.CRUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Write 10 records to a new file
        List<Record> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) records.add(new Record(i, 'A', 'B', i * 10));
        RecordManager.writeRecords(records);

        // Add 5 entries to the end of the file
        List<Record> additionalRecords = new ArrayList<>();
        for (int i = 10; i < 15; i++) additionalRecords.add(new Record(i, 'C', 'D', i * 10));
        RecordManager.appendRecords(additionalRecords);

        // Calling all entries
        System.out.println("All records:");
        RecordManager.printRecords();

        // Delete the 3rd entry and display all entries again
        RecordManager.removeRecord(2);
        System.out.println("After removing the third record:");
        RecordManager.printRecords();

        // Update the 5th entry and display all entries again
        Record updatedRecord = new Record(4, 'W', 'W', 40);
        RecordManager.updateRecord(4, updatedRecord);
        System.out.println("After updating the fifth record:");
        RecordManager.printRecords();

        // Display the number of records
        int count = RecordManager.countRecords();
        System.out.println("Number of records: " + count);

    }
}
