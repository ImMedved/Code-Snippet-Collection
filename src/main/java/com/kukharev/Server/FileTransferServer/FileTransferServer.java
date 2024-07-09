//A Java application to streamline the transfer of large files (1GB+) over TCP/IP using Java Sockets.

package com.kukharev.Server.FileTransferServer;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.Arrays;

public class FileTransferServer {
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB chunks

    public static void main(String[] args) throws IOException {
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();

        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            // Handshake: Get file size and checksum
            long fileSize = in.readLong();
            byte[] clientChecksum = new byte[32];
            in.readFully(clientChecksum);

            // Initialize file output stream
            File outputFile = new File("received_largefile.dat");
            try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {

                // Transfer data
                byte[] buffer = new byte[CHUNK_SIZE];
                int bytesRead;
                long totalRead = 0;
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                while (totalRead < fileSize) {
                    bytesRead = in.read(buffer, 0, (int) Math.min(CHUNK_SIZE, fileSize - totalRead));
                    if (bytesRead < 0) break;
                    md.update(buffer, 0, bytesRead);
                    totalRead += bytesRead;
                    fileOut.write(buffer, 0, bytesRead);
                    // ... Update progress
                    System.out.printf("Progress: %.2f%%%n", (totalRead * 100.0) / fileSize);
                }

                // Send checksum for verification
                byte[] serverChecksum = md.digest();
                out.write(serverChecksum);

                // Checksum comparison
                if (Arrays.equals(clientChecksum, serverChecksum)) {
                    out.writeBoolean(true);
                } else {
                    out.writeBoolean(false);
                }
            } catch (Exception e) {
                // Handle file output stream exceptions
                e.printStackTrace();
            }
        } catch (Exception e) {
            // Handle exceptions such as IOException, NoSuchAlgorithmException
            e.printStackTrace();
        } finally {
            clientSocket.close();
            serverSocket.close();
        }
    }
}

