package com.example.awss3.controller;

import com.example.awss3.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("s3bucket")
@CrossOrigin("*")
public class BucketController {

    @Autowired
    BucketService service;

    @GetMapping("/buckets/{bucketName}")
    public ResponseEntity<?> getDetailBucket(@PathVariable String bucketName) {
        return new ResponseEntity<>(service.getDetailBucket(bucketName), HttpStatus.OK);
    }

    /**
     * Calls Service class to create bucket on AWS S3
     *
     * @param bucketName
     * @return
     */
    @PostMapping("/buckets/{bucketName}")
    public ResponseEntity<String> createBucket(@PathVariable String bucketName) {
        return new ResponseEntity<>(service.createBucket(bucketName), HttpStatus.OK);
    }


    /**
     *
     * @param bucketName
     * @return
     */
    @DeleteMapping("/bucketa/{bucketName}")
    public ResponseEntity<String> deleteBucket(@PathVariable String bucketName) {
        return new ResponseEntity<>(service.deleteBucket(bucketName), HttpStatus.OK);
    }

}