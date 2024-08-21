package com.example.springjparest.onetoone.sharedpk.rest;

import com.example.springjparest.onetoone.sharedpk.mapper.PersonMapper;
import com.example.springjparest.onetoone.sharedpk.model.Person;
import com.example.springjparest.onetoone.sharedpk.model.PersonDetail;
import com.example.springjparest.onetoone.sharedpk.rest.dto.CreatePersonAllFieldRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.PersonResponse;
import com.example.springjparest.onetoone.sharedpk.rest.dto.UpdatePersonAllFieldRequest;
import com.example.springjparest.onetoone.sharedpk.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons2")
public class Person2Controller {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping
    public List<PersonResponse> getAllPerson() {
        List<Person> persons = personService.getAllPerson();
        return persons.stream().map(personMapper::toPersonResponse).toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonResponse createPerson(@Valid @RequestBody CreatePersonAllFieldRequest request) {
        // Main object
        Person person = personMapper.createPersonFromRequest(request);
        // Get sub object and set foreign key
        PersonDetail personDetail = personMapper.createPersonDetailFromRequest(request);
        personDetail.setPerson(person);

        // Set foreign key to main object
        person.setPersonDetail(personDetail);
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }

    @PutMapping("/{personId}")
    public PersonResponse updatePerson(@PathVariable String personId,
                                       @Valid @RequestBody UpdatePersonAllFieldRequest request) {

        Person person = personMapper.updatePersonFromRequest(request);

        PersonDetail personDetail = personMapper.updatePersonDetailFromRequest(request);

        personDetail.setPerson(person);
        person.setPersonDetail(personDetail);

        person = personService.updatePerson(person, personId);
        return personMapper.toPersonResponse(person);
    }

    @DeleteMapping("/{personId}")
    public PersonResponse deletePerson(@PathVariable String personId) {
        Person person = personService.validateAndGetPerson(personId);
        personService.deletePerson(person);
        return personMapper.toPersonResponse(person);
    }
}