package org.anhvt.schoolservice.service;

import org.anhvt.schoolservice.entity.School;
import org.anhvt.schoolservice.entity.Student;
import org.anhvt.schoolservice.payload.FullSchoolResponse;
import org.anhvt.schoolservice.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository repository;

    @Autowired
    private StudentClient client;

    public void saveSchool(School school) {
        repository.save(school);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        School school = repository.findById(schoolId)
                .orElse(null);
        List<Student> students = client.findAllStudentsBySchool(schoolId);
        return new FullSchoolResponse(school.getName(), school.getEmail(), students);
    }
}