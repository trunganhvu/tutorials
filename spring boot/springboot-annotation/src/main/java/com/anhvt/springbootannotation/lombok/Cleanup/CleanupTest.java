/**
 * Copyright 2024
 * Name: CleanupTest
 */
package com.anhvt.springbootannotation.lombok.Cleanup;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Cleanup is short way code to close threads (Closeable, AutoCloseable)
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class CleanupTest {
    public static void main(String[] args) throws IOException {
        withCleanup();

        System.out.println("=====");

        withoutCleanup();
    }

    static InputStream inputStreamFirst = null;
    static BufferedReader readerFirst = null;

    private static void withCleanup() throws IOException {
        {
            @Cleanup
            InputStream inputStream = CleanupTest.class.getResourceAsStream("/example.txt");    // src/main/resources/example.txt
            @Cleanup
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            inputStreamFirst = inputStream;
            readerFirst = reader;

            //  Check InputStream and BufferedReader closed
            checkInputStreamClosed(inputStreamFirst);           // inputStream opened
            checkBufferedReaderClosed(readerFirst);             // bufferedReader opened

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line);                         // 234
            }
            System.out.println();
        }

        // Check InputStream and BufferedReader closed
        checkInputStreamClosed(inputStreamFirst);               // inputStream has been closed!
        checkBufferedReaderClosed(readerFirst);                 // bufferedReader has been closed!
    }

    private static void withoutCleanup() {
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = CleanupTest.class.getResourceAsStream("/example.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));

            inputStreamFirst = inputStream;
            readerFirst = reader;

            //  Check InputStream and BufferedReader closed
            checkInputStreamClosed(inputStreamFirst);                   // inputStream opened
            checkBufferedReaderClosed(readerFirst);                     // bufferedReader opened

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line);                                 // 234
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Check InputStream and BufferedReader closed
        checkInputStreamClosed(inputStreamFirst);                       // inputStream has been closed!
        checkBufferedReaderClosed(readerFirst);                         // bufferedReader has been closed!
    }

    private static void checkInputStreamClosed(InputStream inputStream) {
        try {
            inputStream.read();  // Check againt
            System.out.println("inputStream opened");
        } catch (IOException e) {
            System.out.println("inputStream has been closed!");
        }
    }

    private static void checkBufferedReaderClosed(BufferedReader bufferedReader) {
        try {
            bufferedReader.read();  // Check againt
            System.out.println("bufferedReader opened");
        } catch (IOException e) {
            System.out.println("bufferedReader has been closed!");
        }
    }
}
