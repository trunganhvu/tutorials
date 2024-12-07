package org.anhvt.springbootmasksensitivelog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anhvt.springbootmasksensitivelog.entity.Person;
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
                new Person("Foo", "Bar", 25, 123456789, "ABC XYZ Street"),
                new Person("Baz", "Qux", 30, 987654321, "MNO PQR Street")
        );

        logger.info("Person List {}", personList);

        String jsonOutput = new ObjectMapper().writeValueAsString(personList);

        logger.info("Person List - JSON output {}", jsonOutput);

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}
