package com.example.springjparest.manytomany.simplepk.service;

import com.example.springjparest.manytomany.simplepk.model.Book;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateBookWritersRequest;

public interface BookService {

    Book validateAndGetBook(String id);

    Book saveBook(Book book);

    void deleteBook(Book book);

    Book createBookAndWriters(CreateBookWritersRequest createBookWritersRequest);
}
