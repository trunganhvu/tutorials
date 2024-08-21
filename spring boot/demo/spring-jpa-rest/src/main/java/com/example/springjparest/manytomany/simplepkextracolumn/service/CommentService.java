package com.example.springjparest.manytomany.simplepkextracolumn.service;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Comment;

public interface CommentService {

    Comment validateAndGetComment(String id);

    Comment saveComment(Comment comment);

    void deleteComment(Comment comment);
}
