package org.anhvt.springbooti18n.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.anhvt.springbooti18n.config.Translator.translate;

@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @GetMapping("message")
    public String getMessage(){
        return translate("message.greeting");
    }
}
