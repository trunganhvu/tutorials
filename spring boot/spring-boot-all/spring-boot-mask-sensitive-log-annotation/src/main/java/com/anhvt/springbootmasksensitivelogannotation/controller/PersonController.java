/**
 * Copyright 2024
 * Name: PersonController2
 */
package com.anhvt.springbootmasksensitivelogannotation.controller;

import com.anhvt.springbootmasksensitivelogannotation.config.LogMaskUtil;
import com.anhvt.springbootmasksensitivelogannotation.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersonList() throws JsonProcessingException {
        List<Person> personList = List.of(
                new Person("Foo", "Bar", 25, 123456789, "ABC XYZ Street")
        );

        // Person List [com.anhvt.springbootmasksensitivelogannotation.entity.Person[ firstName=Foo, lastName=Bar, age=25, creditCardNumber=*****3456789, address=*****,  ]]
        logger.info("Person List {}", personList);

        // Person List - JSON output [{"firstName":"Foo","lastName":"Bar","age":25,"creditCardNumber":123456789,"address":"ABC XYZ Street"}]
        String jsonOutput = new ObjectMapper().writeValueAsString(personList);
        logger.info("Person List - JSON output {}", jsonOutput);

        // Masked Person: com.anhvt.springbootmasksensitivelogannotation.entity.Person@59f25a41[address=*****,age=25,creditCardNumber=*****3456789,firstName=Foo,lastName=Bar] 123456789
        personList.forEach(person -> logger.info("Masked Person: {} {}", LogMaskUtil.toString(person), person.getCreditCardNumber().toString()));

        // Masked Person: com.anhvt.springbootmasksensitivelogannotation.entity.Person[ firstName=Foo, lastName=Bar, age=25, creditCardNumber=*****3456789, address=*****,  ]
        personList.forEach(person -> logger.info("Masked Person: {}", person.toString()));

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}
