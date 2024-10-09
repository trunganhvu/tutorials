/**
 * Copyright 2024
 * Name: UploadFileResponse
 */
package com.anhvt.springbootuploaddownload.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/9/2024
 */
@Setter
@Getter
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
