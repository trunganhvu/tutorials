package com.example.springjparest.manytomany.simplepkextracolumn.rest.dto;

import java.util.Set;

public record ArticleResponse(String id, String title, Set<Comment> comments) {

    public record Comment(String id, String text, Reviewer reviewer) {

        public record Reviewer(String id) {
        }
    }
}
