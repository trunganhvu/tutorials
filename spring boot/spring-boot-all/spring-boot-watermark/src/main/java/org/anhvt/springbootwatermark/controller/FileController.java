package org.anhvt.springbootwatermark.controller;

import org.anhvt.springbootwatermark.model.ResponseMessage;
import org.anhvt.springbootwatermark.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/watermark")
    public ResponseEntity<ResponseMessage> watermarkFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {

            message = storageService.watermarkFile(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/resize")
    public ResponseEntity<ResponseMessage> resizeFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            message = storageService.resizedImage(file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/remove-watermark")
    public ResponseEntity<?> removeWatermark(@RequestParam("file") MultipartFile file) {
        try {
            File unwatermarkedFile = storageService.removeWatermark(file);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + unwatermarkedFile.getName())
                    .body(Files.readAllBytes(unwatermarkedFile.toPath()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }

}