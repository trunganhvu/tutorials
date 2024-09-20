/**
 * Copyright 2024
 * Name: EmailController
 */
package com.anhvt.springbootmail.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/20/2024
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;
    
    private final String MAIL_TO = "mail@gmail.com";

    private final String SUBJECT = "THIS IS A MOCK SUBJECT";
    

    @RequestMapping("sendSimpleEmail")
    public String sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(MAIL_TO);
            message.setSubject(SUBJECT);
            message.setText("This is a body text. <p style='color:#42b983'>Hello World</p>");
            jms.send(message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendHtmlEmail")
    public String sendHtmlEmail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(MAIL_TO);
            message.setSubject(SUBJECT);
            // HTML
            StringBuffer sb = new StringBuffer(
                    "<p style='color:#42b983'>Hello World</p>");
            helper.setText(sb.toString(), true);
            jms.send(message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendAttachmentsMail")
    public String sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(MAIL_TO);
            message.setSubject(SUBJECT);
            message.setText("This is a body text");
            FileSystemResource file = new FileSystemResource(
                    new File("src/main/resources/static/file/20240920.txt"));
            helper.addAttachment("20240920.txt", file);
            jms.send(message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendInlineMail")
    public String sendInlineMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(MAIL_TO);
            helper.setSubject(SUBJECT);
            helper.setText("<html><body>Image：<img src='cid:img'/></body></html>", true);
            FileSystemResource file = new FileSystemResource(
                    new File("src/main/resources/static/file/image.png"));
            helper.addInline("img", file);
            jms.send(message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendTemplateEmail")
    public String sendTemplateEmail(String code) {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(MAIL_TO);
            helper.setSubject(SUBJECT);

            Context context = new Context();
            context.setVariable("code", code);
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template, true);
            jms.send(message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
