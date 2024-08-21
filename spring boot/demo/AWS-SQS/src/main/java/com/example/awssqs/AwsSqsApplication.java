package com.example.awssqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AwsSqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSqsApplication.class, args);
    }

}
