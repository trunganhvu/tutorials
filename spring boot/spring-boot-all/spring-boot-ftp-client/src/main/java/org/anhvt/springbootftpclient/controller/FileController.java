package org.anhvt.springbootftpclient.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.anhvt.springbootftpclient.ftp.FTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FTPService ftpService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try (var inputStream = file.getInputStream()) {
            boolean success = ftpService.uploadFile("/uploads/" + file.getOriginalFilename(), inputStream);
            return success
                    ? ResponseEntity.ok("File uploaded successfully!")
                    : ResponseEntity.status(500).body("Failed to upload file");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        try (var outputStream = response.getOutputStream()) {
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            ftpService.downloadFile("/uploads/" + fileName, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        boolean success = ftpService.deleteFile("/uploads/" + fileName);
        return success
                ? ResponseEntity.ok("File deleted successfully!")
                : ResponseEntity.status(500).body("Failed to delete file");
    }
}
