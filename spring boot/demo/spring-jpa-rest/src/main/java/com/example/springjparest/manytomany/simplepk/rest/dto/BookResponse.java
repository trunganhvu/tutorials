package com.example.springjparest.manytomany.simplepk.rest.dto;

import java.util.List;

public record BookResponse(String id, String name, List<Writer> writers) {

    public record Writer(String id, String name) {
    }
}
