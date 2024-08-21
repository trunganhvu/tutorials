package com.example.springjparest.manytomany.simplepkextracolumn.rest.dto;

public record CommentResponse(String id, Reviewer reviewer, Article article, String text) {

    public record Reviewer(String id, String name) {
    }

    public record Article(String id, String title) {
    }
}
