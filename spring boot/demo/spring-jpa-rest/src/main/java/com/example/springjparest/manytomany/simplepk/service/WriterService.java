package com.example.springjparest.manytomany.simplepk.service;

import com.example.springjparest.manytomany.simplepk.model.Book;
import com.example.springjparest.manytomany.simplepk.model.Writer;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateWriterBooksRequest;

public interface WriterService {

    Writer validateAndGetWriter(String id);

    Writer saveWriter(Writer writer);

    void deleteWriter(Writer writer);

    Writer createWriterAndBooks(CreateWriterBooksRequest request);
}
