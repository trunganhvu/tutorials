/**
 * Copyright 2024
 * Name: FileStorageProperties
 */
package com.anhvt.springbootuploaddownload.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/9/2024
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
}
