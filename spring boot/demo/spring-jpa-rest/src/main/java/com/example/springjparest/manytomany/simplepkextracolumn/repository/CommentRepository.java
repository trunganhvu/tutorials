package com.example.springjparest.manytomany.simplepkextracolumn.repository;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
}
