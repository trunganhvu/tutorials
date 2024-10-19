package com.anhvt.springbooth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootH2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

}
