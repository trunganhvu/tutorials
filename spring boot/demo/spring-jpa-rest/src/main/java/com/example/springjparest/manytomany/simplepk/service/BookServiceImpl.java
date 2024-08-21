package com.example.springjparest.manytomany.simplepk.service;

import com.example.springjparest.manytomany.simplepk.exception.BookNotFoundException;
import com.example.springjparest.manytomany.simplepk.mapper.BookMapper;
import com.example.springjparest.manytomany.simplepk.model.Book;
import com.example.springjparest.manytomany.simplepk.model.Writer;
import com.example.springjparest.manytomany.simplepk.repository.BookRepository;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateBookWritersRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Book validateAndGetBook(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    @Override
    public Book createBookAndWriters(CreateBookWritersRequest createBookWritersRequest) {
        Book book = bookMapper.createBookFromBookWriters(createBookWritersRequest);
        List<String> writerNames = createBookWritersRequest.getWriterNames();
        if (!writerNames.isEmpty()) {
            Set<Writer> writerSet = new LinkedHashSet<>();
            Book finalBook = book;

            writerSet = writerNames.stream().map(name -> {
                Writer writer = new Writer();
                writer.addBook(finalBook);
                writer.setName(name);
                return writer;
            }).collect(Collectors.toSet());

            book.addWriters(writerSet);
        }

        book = saveBook(book);

        return book;
    }
}
