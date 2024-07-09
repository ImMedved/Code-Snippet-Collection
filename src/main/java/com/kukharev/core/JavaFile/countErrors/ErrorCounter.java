package com.kukharev.core.JavaFile.countErrors;

/**
 * This program reads a text file and counts the number of occurrences of the string "ERROR" (case-insensitive).
 *
 * The program takes no arguments, but requires the file path to be defined in the `filePath` variable.
 * It utilizes the `countErrors` method to read the file line by line and check for the presence of "ERROR"
 * (converted to uppercase for case-insensitive comparison). Finally, it prints the total number of occurrences to the console.
 *
 * @throws IOException If there's an error reading the file.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ErrorCounter {

    /**
     * Counts the number of times the string "ERROR" appears (case-insensitive) in a text file.
     *
     * This method takes the path to the text file as input and returns the number of times the string "ERROR"
     * appears in the file content (ignoring case sensitivity).
     *
     * @param filePath The path to the text file to be analyzed.
     * @return The number of occurrences of the string "ERROR" (case-insensitive) in the file.
     * @throws IOException If there's an error reading the file.
     */
    public static int countErrors(String filePath) throws IOException {
        int errorCount = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Convert the line to uppercase for case-insensitive comparison
                if (line.toUpperCase().contains("ERROR")) {
                    errorCount++;
                }
            }
        }
        return errorCount;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "test.txt"; // Replace with your actual file path
        int errorCount = countErrors(filePath);
        System.out.println("Number of 'ERROR' occurrences: " + errorCount);
    }
}