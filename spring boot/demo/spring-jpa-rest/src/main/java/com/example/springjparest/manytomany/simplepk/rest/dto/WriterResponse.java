package com.example.springjparest.manytomany.simplepk.rest.dto;

import java.util.List;

public record WriterResponse(String id, String name, List<Book> books) {

    public record Book(String id, String name) {
    }
}
