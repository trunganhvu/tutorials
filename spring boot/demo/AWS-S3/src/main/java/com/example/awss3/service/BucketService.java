package com.example.awss3.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {

    @Autowired
    private FileStore fileStore;

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    /**
     * Calls FileStore.java class to create bucket on AWS S3
     *
     * @param bucketName
     * @return
     */
    public String createBucket(String bucketName) {
        logger.info("Inside create bucket method");
        return fileStore.createBucket(bucketName);
    }

    /**
     *
     * @param bucketName
     * @return
     */
    public Object getDetailBucket(String bucketName) {
        logger.info("Inside detail bucket method");
        return fileStore.getDetailBucket(bucketName);
    }

    /**
     *
     * @param bucketName
     * @return
     */
    public String deleteBucket(String bucketName) {
        fileStore.deleteBucket(bucketName);
        return "Bucket deleted successfully";
    }

}