package com.anhvt.springboottransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringBootTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTransactionApplication.class, args);
    }

}
