package com.example.springjparest.onetoone.sharedpk.service;

import com.example.springjparest.onetoone.sharedpk.model.Person;

import java.util.List;

public interface PersonService {

    Person validateAndGetPerson(String id);

    Person savePerson(Person person);

    void deletePerson(Person person);

    List<Person> getAllPerson();

    Person updatePerson(Person person, String personId);
}
