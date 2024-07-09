/*
A Java CLI application that, given a directory path, generates a graph representation (as a text file) of the media files found within the directory and subdirectories.
The graph represents directories as nodes, and the edges should represent media files, connecting the directory node to a node representing the file type (e.g., 'mp3', 'mp4').
The program supports at least 'mp3' and 'mp4' file types.
 */

package com.kukharev.core.JavaFile.MediaGraphGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MediaGraphGenerator {

    /**
     * Generates a media graph for the given directory path and writes it to the specified output file.
     *
     * @param directoryPath The path of the directory to scan for media files.
     * @param outputFilePath The path of the output file where the graph will be written.
     */
    public static void generateMediaGraph(String directoryPath, String outputFilePath) {
        File dir = new File(directoryPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            generateGraph(dir, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursively generates a graph representation of media files in the given directory.
     *
     * @param dir The directory to scan.
     * @param writer The BufferedWriter to write the graph to.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateGraph(File dir, BufferedWriter writer) throws IOException {
        if (dir.isDirectory()) {
            writer.write("Directory: " + dir.getName());
            writer.newLine();
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        generateGraph(file, writer);
                    } else {
                        String fileName = file.getName();
                        String fileExtension = getFileExtension(fileName);
                        if (fileExtension.equals("mp3") || fileExtension.equals("mp4")) {
                            writer.write(fileExtension + " -> " + file.getPath());
                            writer.newLine();
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the file extension of the given file name.
     *
     * @param fileName The name of the file.
     * @return The file extension, or an empty string if there is no extension.
     */
    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0) {
            return fileName.substring(lastIndex + 1);
        }
        return "";
    }

    /**
     * The main method to run the MediaGraphGenerator from the command line.
     *
     * @param args Command line arguments:
     *             args[0] - the directory path to scan (optional, defaults to current directory).
     *             args[1] - the output file path (optional, defaults to "./media_graph.txt").
     */
    public static void main(String[] args) {
        String directoryPath = args.length > 0 ? args[0] : ".";
        String outputFilePath = args.length > 1 ? args[1] : "./media_graph.txt";
        generateMediaGraph(directoryPath, outputFilePath);
    }
}