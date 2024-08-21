package com.example.springjparest.manytomany.simplepkextracolumn.rest.dto;

import java.util.Set;

public record ReviewerResponse(String id, String name, Set<Comment> comments) {

    public record Comment(String id, String text, Article article) {

        public record Article(String id) {
        }
    }
}
