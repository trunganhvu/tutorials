package com.example.springjparest.manytomany.simplepk.repository;

import com.example.springjparest.manytomany.simplepk.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
