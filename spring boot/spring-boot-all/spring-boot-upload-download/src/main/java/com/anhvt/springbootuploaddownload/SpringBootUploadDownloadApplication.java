package com.anhvt.springbootuploaddownload;

import com.anhvt.springbootuploaddownload.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SpringBootUploadDownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUploadDownloadApplication.class, args);
    }

}
