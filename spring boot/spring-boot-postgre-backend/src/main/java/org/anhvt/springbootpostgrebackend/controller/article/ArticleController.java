package org.anhvt.springbootpostgrebackend.controller.article;

import org.anhvt.springbootpostgrebackend.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/ping")
    public String ping() {
        return articleService.pingArticle();
    }
}
