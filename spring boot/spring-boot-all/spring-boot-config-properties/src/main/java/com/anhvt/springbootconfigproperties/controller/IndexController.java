/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootconfigproperties.controller;

import com.anhvt.springbootconfigproperties.configuration.BlogProperties1;
import com.anhvt.springbootconfigproperties.configuration.BlogProperties2;
import com.anhvt.springbootconfigproperties.configuration.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@RestController
@Slf4j
public class IndexController {
    @Autowired
    private BlogProperties1 blogProperties1;
    @Autowired
    private BlogProperties2 blogProperties2;
    @Autowired
    private UserProperties userProperties;

    @RequestMapping("/")
    String index() {
        log.info(blogProperties1.getName() + " " + blogProperties1.getTitle());     // anhvt "java developer"
        log.info(blogProperties2.getName()
                + " "
                + blogProperties2.getTitle()
                + " "
                + blogProperties2.getHeader());  // anhvt "java developer" anhvt--"java developer"
        log.info(userProperties.getName()
                + " "
                + userProperties.getAge()
                + " "
                + userProperties.getFullname());     // Admin 25 anhvt (Admin is a account in PC)
        return "done";
    }
}
