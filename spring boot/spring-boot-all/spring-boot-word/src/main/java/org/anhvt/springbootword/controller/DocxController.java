package org.anhvt.springbootword.controller;

import org.anhvt.springbootword.service.DocxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docx")
public class DocxController {
    @Autowired
    private DocxService docxService;


    @GetMapping("/export")
    public ResponseEntity<byte[]> exportDocx() {
        // Trả file về client
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("example.docx").build());

        return new ResponseEntity<>(docxService.exportDocx(), headers, HttpStatus.OK);
    }

    @GetMapping("/export-template")
    public ResponseEntity<byte[]> exportDocxTemplate() {
        // Trả file về client
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("example.docx").build());

        return new ResponseEntity<>(docxService.exportDocxFromTemplate(), headers, HttpStatus.OK);
    }
}
