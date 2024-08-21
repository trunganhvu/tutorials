package com.example.springjparest.manytomany.simplepkextracolumn.service;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Article;

public interface ArticleService {

    Article validateAndGetArticle(String id);

    Article createArticle(Article article);

    void deleteArticle(Article article);
}
