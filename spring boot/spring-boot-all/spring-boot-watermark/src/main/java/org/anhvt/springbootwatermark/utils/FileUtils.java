package org.anhvt.springbootwatermark.utils;

import java.io.File;

public class FileUtils {
    public static String getExtension(File file) {
        String fileName = file.getName();
        String[] ext = fileName.split("\\.");
        return ext[ext.length - 1];
    }

    public static String getFileNameWithoutExtension(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IllegalArgumentException("File name is invalid");
        }
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return originalFileName.substring(0, dotIndex);
        }
        return originalFileName;
    }
}
