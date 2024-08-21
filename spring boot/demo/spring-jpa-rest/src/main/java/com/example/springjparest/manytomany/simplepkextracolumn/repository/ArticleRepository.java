package com.example.springjparest.manytomany.simplepkextracolumn.repository;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, String> {
}
