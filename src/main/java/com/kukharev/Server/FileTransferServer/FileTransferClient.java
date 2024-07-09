package com.kukharev.Server.FileTransferServer;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;

public class FileTransferClient {
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB chunks

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 5000;
        Socket socket = new Socket(host, port);

        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             FileInputStream fileInput = new FileInputStream("largefile.dat")) {

            // Handshake: Send file size and checksum
            File file = new File("largefile.dat");
            long fileSize = file.length();
            out.writeLong(fileSize);
            out.write(calculateChecksum(file));

            // Transfer data
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            long totalSent = 0;
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalSent += bytesRead;
                // ... Update progress
                System.out.printf("Progress: %.2f%%%n", (totalSent * 100.0) / fileSize);
            }

            // Receive and validate checksum
            byte[] serverChecksum = new byte[32];
            in.readFully(serverChecksum);
            boolean success = in.readBoolean();

            if (success) {
                System.out.println("File transferred successfully.");
            } else {
                System.out.println("File transfer failed. Checksums do not match.");
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    private static byte[] calculateChecksum(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[CHUNK_SIZE];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        return digest.digest();
    }
}
