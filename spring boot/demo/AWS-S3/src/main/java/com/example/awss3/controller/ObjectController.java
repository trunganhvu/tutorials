package com.example.awss3.controller;

import com.example.awss3.dto.FileDownloadResponseDto;
import com.example.awss3.service.BucketService;
import com.example.awss3.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("s3bucket/buckets")
@CrossOrigin("*")
public class ObjectController {
    @Autowired
    ObjectService service;

    /**
     * Calls Service class to upload file on existing bucket
     *
     * @param file
     * @param bucketName
     * @return
     */
    @PostMapping(path = "/{bucketName}/objects", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file,
                                             @PathVariable String bucketName) {
        return new ResponseEntity<>(service.uploadFile(file,bucketName), HttpStatus.OK);
    }

    @GetMapping("/{bucketName}/objects/{filename}")
    public ResponseEntity<?> getObjectDetail(@PathVariable String bucketName,
                                             @PathVariable String filename) {
        return new ResponseEntity<>(service.getObjectFromBucket(filename,bucketName), HttpStatus.OK);

    }

    @GetMapping("/{bucketName}/objects/{filename}/download")
    public ResponseEntity<?> downloadObject(@PathVariable String bucketName,
                                             @PathVariable String filename) throws IOException {
        FileDownloadResponseDto response = service.downloadFile(filename,bucketName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(response.getContentType()));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + response.getOriginalFilename());
        return ResponseEntity.ok()
                .headers(headers)
                .body(response.getFileContent());

    }


    @DeleteMapping(path="/{bucketName}/objects/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String bucketName,@PathVariable String fileName)
    {
        return new ResponseEntity<>(service.deleteFile(bucketName,fileName),HttpStatus.OK);
    }
}
