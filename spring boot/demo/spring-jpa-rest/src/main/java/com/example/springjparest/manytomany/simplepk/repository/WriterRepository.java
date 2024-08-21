package com.example.springjparest.manytomany.simplepk.repository;

import com.example.springjparest.manytomany.simplepk.model.Writer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends CrudRepository<Writer, String> {
}
