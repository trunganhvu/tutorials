package com.example.springjparest.onetoone.sharedpk.service;

import com.example.springjparest.onetoone.sharedpk.exception.PersonNotFoundException;
import com.example.springjparest.onetoone.sharedpk.model.Person;
import com.example.springjparest.onetoone.sharedpk.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person validateAndGetPerson(String id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person updatePerson(Person person, String personId) {
        Person currentPerson = validateAndGetPerson(personId);
        currentPerson.setName(person.getName());
        currentPerson.getPersonDetail().setDescription(person.getPersonDetail().getDescription());
        return savePerson(currentPerson);
    }
}
