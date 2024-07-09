package com.kukharev.Spring;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ImageReader {
    // This is the cache to store recently accessed images.
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    // This is the method to read an image file securely.
    public static BufferedImage readImageFileSecurely(String filePath) throws IOException {
        File imageFile = new File(filePath);
        // Validate the existence and readability of the file.
        validateFile(imageFile);

        // Return the cached image if it exists.
        if (cache.containsKey(filePath)) {
            return cache.get(filePath);
        }

        // Read the image using 'ImageIO'.
        BufferedImage image = ImageIO.read(imageFile);

        // Check if the image format is supported.
        if (image == null) {
            throw new UnsupportedImageFormatException("Unsupported image format for file: " + filePath);
        }

        // Cache the image for future use.
        cache.put(filePath, image);
        return image;
    }

    // This is the method to validate the existence and readability of the file.
    private static void validateFile(File file) throws FileNotFoundException {
        if (file.exists() || file.canRead()) {
            throw new FileNotFoundException("File does not exist or cannot be read: " + file.getPath());
        }
    }

    // Custom an exception for unsupported image formats.
    public static class UnsupportedImageFormatException extends IOException {
        public UnsupportedImageFormatException(String message) {
            super(message);
        }
    }
}