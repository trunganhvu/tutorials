/**
 * Copyright 2024
 * Name: DataLoader
 */
package com.anhvt.springbootbenchmarkinsert.config;

import com.anhvt.springbootbenchmarkinsert.entity.ChildNoRelation;
import com.anhvt.springbootbenchmarkinsert.entity.ChildRelation;
import com.anhvt.springbootbenchmarkinsert.entity.ParentNoRelation;
import com.anhvt.springbootbenchmarkinsert.entity.ParentRelation;
import com.anhvt.springbootbenchmarkinsert.repository.ChildNoRelationRepository;
import com.anhvt.springbootbenchmarkinsert.repository.ChildRelationRepository;
import com.anhvt.springbootbenchmarkinsert.repository.ParentNoRelationRepository;
import com.anhvt.springbootbenchmarkinsert.repository.ParentRelationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ParentRelationRepository parentRelationRepository;

    @Autowired
    private ChildRelationRepository childRelationRepository;

    @Autowired
    private ParentNoRelationRepository parentNoRelationRepository;

    @Autowired
    private ChildNoRelationRepository childNoRelationRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start 1 insert");
        long startTimeWithForeignKey = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParentRelation parent = new ParentRelation();
            parent.setName("Parent " + i);
            parent.setCity("City " + i);
            parent.setAddress1("Address " + i);
            parent.setAddress2("Address " + i);
            parent.setCity("City " + i);
            parent.setState("State " + i);
            parent.setZip("Zip " + i);
            parent.setDeleted(0);
            parent.setCreatedBy(Instant.now());
            parent.setUpdatedBy(Instant.now());
            parentRelationRepository.save(parent);

            ChildRelation child = new ChildRelation();
            child.setName("Child " + i);
            child.setParent(parent);
            child.setDescription("Description " + i);
            child.setNote("Note " + i);

            childRelationRepository.save(child);
        }
        long endTimeWithForeignKey = System.currentTimeMillis();
        System.out.println("Time taken with foreign key: " + (endTimeWithForeignKey - startTimeWithForeignKey) + " ms");

        System.out.println("Start 2 insert");
        long startTimeWithForeignKey2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParentNoRelation parent = new ParentNoRelation();
            parent.setName("Parent " + i);
            parent.setCity("City " + i);
            parent.setAddress1("Address " + i);
            parent.setAddress2("Address " + i);
            parent.setCity("City " + i);
            parent.setState("State " + i);
            parent.setZip("Zip " + i);
            parent.setDeleted(0);
            parent.setCreatedBy(Instant.now());
            parent.setUpdatedBy(Instant.now());
            parentNoRelationRepository.save(parent);

            ChildNoRelation child = new ChildNoRelation();
            child.setName("Child " + i);
            child.setParent(parent);
            child.setDescription("Description " + i);
            child.setNote("Note " + i);

            childNoRelationRepository.save(child);
        }
        long endTimeWithForeignKey2 = System.currentTimeMillis();
        System.out.println("Time taken with foreign key: " + (endTimeWithForeignKey2 - startTimeWithForeignKey2) + " ms");


    }
}
