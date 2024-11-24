package org.anhvt.springbootmasterslavepostgre.service;

import org.anhvt.springbootmasterslavepostgre.entity.Test;
import org.anhvt.springbootmasterslavepostgre.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Transactional(readOnly = true)
    public Test findById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public void save(Long id, String name) {
        testRepository.save(new Test(id, name));
    }

    @Transactional
    public void update(Long id, String name) {
        Test test = testRepository.findById(id).orElse(null);
        test.setName(name);
        testRepository.save(new Test(id, name));
    }
}
