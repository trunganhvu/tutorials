package com.example.awss3.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileDownloadResponseDto {
    private byte[] fileContent;
    private String originalFilename;
    private String contentType;
}
