package com.anhvt.springbootlifecycle;

import com.anhvt.springbootlifecycle.configuration.WebConfig;
import com.anhvt.springbootlifecycle.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBootLifecycleApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootLifecycleApplication.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        System.out.println("AnnotationConfigApplicationContext");
//        User user = context.getBean(User.class);
//        System.out.println(user.getName());
//        // IOC
//        context.close();
    }

}
