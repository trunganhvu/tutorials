package org.anhvt.springbootpostgrebackend.controller.article;

import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.payload.response.auth.AuthResponse;
import org.anhvt.springbootpostgrebackend.service.article.ArticleService;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
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
    public APIResponse<Object> test() {
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response("test")
                .build();
    }

    @GetMapping("/ping")
    public APIResponse<Object> ping() {
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response("ping")
                .build();
    }
}
