package com.example.springjparest.manytomany.simplepk.service;

import com.example.springjparest.manytomany.simplepk.exception.WriterNotFoundException;
import com.example.springjparest.manytomany.simplepk.mapper.WriterMapper;
import com.example.springjparest.manytomany.simplepk.model.Book;
import com.example.springjparest.manytomany.simplepk.model.Writer;
import com.example.springjparest.manytomany.simplepk.repository.WriterRepository;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateWriterBooksRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    private final WriterMapper writerMapper;

    @Override
    public Writer validateAndGetWriter(String id) {
        return writerRepository.findById(id).orElseThrow(() -> new WriterNotFoundException(id));
    }

    @Override
    public Writer saveWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public void deleteWriter(Writer writer) {
        writerRepository.delete(writer);
    }

    @Transactional
    @Override
    public Writer createWriterAndBooks(CreateWriterBooksRequest request) {
        Writer writer = writerMapper.createWriteFromWriteBooks(request);

        List<String> bookNames = request.getBookNames();
        if (!bookNames.isEmpty()) {
            Set<Book> bookSet = new LinkedHashSet<>();
            Writer finalWriter = writer;
            bookSet = bookNames.stream().map(name -> {
                Book book = new Book();
                book.setName(name);
                book.addWriter(finalWriter);
                return book;
            }).collect(Collectors.toSet());

            writer.addBooks(bookSet);
        }

        writer = saveWriter(writer);
        return writer;
    }
}
