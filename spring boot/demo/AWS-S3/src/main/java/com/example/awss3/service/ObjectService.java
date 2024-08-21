package com.example.awss3.service;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.awss3.dto.FileDownloadResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ObjectService {

    @Autowired
    private FileStore fileStore;

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    /**
     * Method will download file from S3 and will push content to CloudWatch Logs
     *
     * @param fileName
     * @param bucketName
     */
    public FileDownloadResponseDto downloadFile(String fileName, String bucketName) throws IOException {
        try {
            logger.info("File to be fetched from S3 {}", fileName);

            // Get object (content, name, metadata,...)
            S3Object s3Object = fileStore.getObjectFromBucket(fileName, bucketName);

            // Get content steam object
            S3ObjectInputStream objectContent = s3Object.getObjectContent();

            return FileDownloadResponseDto.builder()
                    .fileContent(objectContent.readAllBytes()) // content
                    .contentType(s3Object.getObjectMetadata().getContentType()) // type
                    .originalFilename(s3Object.getKey()) // file name
                    .build();

        } catch (IOException e) {
            logger.error("Error in reading file content {}", e.getMessage());
            throw new IOException(e.getMessage());
        } catch (AmazonS3Exception s3Exception) {
            logger.error("Some error occured", s3Exception.getMessage());
            throw new AmazonS3Exception(s3Exception.getMessage());
        }
    }

    /**
     *
     * @param filename
     * @param bucketName
     * @return
     */
    public S3Object getObjectFromBucket(String filename, String bucketName) {
        return fileStore.getObjectFromBucket(filename, bucketName);
    }

    /**
     * Calls FileStore.java upload file on AWS S3, first validates file is empty if
     * then throw Excepiton
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file, String bucketName) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        try {
            fileStore.uploadFiletoBucket(file, bucketName);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return "File Uploaded Successfully";
    }

    /**
     *
     * @param bucketName
     * @param fileName
     * @return
     */
    public String deleteFile(String bucketName, String fileName) {
        fileStore.deletFile(bucketName,fileName);
        return "File deleted successfully";
    }
}
