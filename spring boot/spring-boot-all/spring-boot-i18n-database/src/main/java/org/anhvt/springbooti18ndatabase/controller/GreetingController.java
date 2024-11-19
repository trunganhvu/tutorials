package org.anhvt.springbooti18ndatabase.controller;

import org.anhvt.springbooti18ndatabase.config.DatabaseMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetingController {

    @Autowired
    private DatabaseMessageSource messageSource;

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        String greetingMessage = messageSource.getMessage("greeting", new Object[]{name}, currentLocale);

        return greetingMessage;
    }
}