/**
 * Copyright 2024
 * Name: TutorialService
 */
package com.anhvt.springbootrediscache.service;

import com.anhvt.springbootrediscache.model.Tutorial;
import com.anhvt.springbootrediscache.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@Service
@EnableCaching
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    @Cacheable("tutorials")
    public List<Tutorial> findAll() {
        doLongRunningTask();
        return tutorialRepository.findAll();
    }

    @Cacheable("tutorials")
    public List<Tutorial> findByTitleContaining(String title) {
        doLongRunningTask();
        return tutorialRepository.findByTitleContaining(title);
    }

    @Cacheable( value = "tutorial", key = "#id")
    public Optional<Tutorial> findById(long id) {
        doLongRunningTask();
        return tutorialRepository.findById(id);
    }

    public Tutorial save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CacheEvict(value = "tutorial", key = "#tutorial.id")
    public Tutorial update(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CacheEvict(value = "tutorial", key = "#id")
    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
    }

    @CacheEvict(value = { "tutorial", "tutorials" }, allEntries = true)
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
