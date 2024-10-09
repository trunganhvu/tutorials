/**
 * Copyright 2024
 * Name: FileStorageException
 */
package com.anhvt.springbootuploaddownload.exception;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/9/2024
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
