package com.example.awscloudwatch.controller;

import com.example.awscloudwatch.service.CloudWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudWatchController {

    @Autowired
    private CloudWatchService service;

    @PostMapping(path = "/publish/{message}")
    public ResponseEntity<String> logMessageToCloudWatch(@PathVariable String message) {
        service.logMessageToCloudWatch(message);
        return new ResponseEntity<>("Message logged to cloudwatch", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test()
    {
        service.logMessageToCloudWatch("test message");
        return "Messaged pushed to cloudwatch";
    }
}
