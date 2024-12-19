package org.anhvt.springbootpostgrebackend.service.article.impl;

import org.anhvt.springbootpostgrebackend.queue.pub.RedisMessagePublisher;
import org.anhvt.springbootpostgrebackend.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private RedisMessagePublisher redisPublisher;

    @Override
    public String pingArticle() {
        System.out.println("pingArticle1");
        redisPublisher.publishArticle("ping");
        System.out.println("pingArticle2");
        return "ping";
    }
}
