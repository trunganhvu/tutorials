package com.example.springjparest.manytomany.simplepkextracolumn.repository;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends CrudRepository<Reviewer, String> {
}
