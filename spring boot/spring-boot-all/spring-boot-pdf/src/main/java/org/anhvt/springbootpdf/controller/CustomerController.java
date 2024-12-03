package org.anhvt.springbootpdf.controller;

import org.anhvt.springbootpdf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/export")
    public String exportCustomer() {
        try {
            customerService.exportCustomer();
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Success";
    }
}
