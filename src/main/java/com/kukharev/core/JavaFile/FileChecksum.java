/*
A Java code reads a file containing student's grades and then calculate the version of the data file.
The function returns checksum as a string.
 */

package com.kukharev.core.JavaFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileChecksum {

    public static String calculateChecksum(String filePath) throws IOException, NoSuchAlgorithmException {
        // Try-with-resources statement to ensure the FileInputStream is closed automatically
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[1024];
            int nRead;
            // Read the file in chunks and update the MessageDigest
            while ((nRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nRead);
            }
            // Convert the final hash to a hexadecimal string and return it
            return bytesToHex(md.digest());
        }
    }

    private static String bytesToHex(byte[] bytes) {
        // Convert a byte array to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
