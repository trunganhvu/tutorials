package com.example.springjparest.manytomany.simplepkextracolumn.rest;

import com.example.springjparest.manytomany.simplepkextracolumn.mapper.ArticleMapper;
import com.example.springjparest.manytomany.simplepkextracolumn.mapper.CommentMapper;
import com.example.springjparest.manytomany.simplepkextracolumn.mapper.ReviewerMapper;
import com.example.springjparest.manytomany.simplepkextracolumn.model.Article;
import com.example.springjparest.manytomany.simplepkextracolumn.model.Comment;
import com.example.springjparest.manytomany.simplepkextracolumn.model.Reviewer;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.ArticleResponse;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.CommentResponse;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.CreateArticleRequest;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.CreateCommentRequest;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.CreateReviewerRequest;
import com.example.springjparest.manytomany.simplepkextracolumn.rest.dto.ReviewerResponse;
import com.example.springjparest.manytomany.simplepkextracolumn.service.ArticleService;
import com.example.springjparest.manytomany.simplepkextracolumn.service.CommentService;
import com.example.springjparest.manytomany.simplepkextracolumn.service.ReviewerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/m2m/simple_extra_column/v1")
public class ReviewerArticleController {

    private final ArticleService articleService;
    private final ReviewerService reviewerService;
    private final CommentService commentService;
    private final ReviewerMapper reviewerMapper;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;

    // --------
    // Reviewer

    @GetMapping("/reviewers/{reviewerId}")
    public ReviewerResponse getReviewer(@PathVariable String reviewerId) {
        Reviewer reviewer = reviewerService.validateAndGetReviewer(reviewerId);
        return reviewerMapper.toReviewerResponse(reviewer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviewers")
    public ReviewerResponse createReviewer(@Valid @RequestBody CreateReviewerRequest createReviewerRequest) {
        Reviewer reviewer = reviewerMapper.toReviewer(createReviewerRequest);
        reviewer = reviewerService.saveReviewer(reviewer);
        return reviewerMapper.toReviewerResponse(reviewer);
    }

    @DeleteMapping("/reviewers/{reviewerId}")
    public ReviewerResponse deleteReviewer(@PathVariable String reviewerId) {
        Reviewer reviewer = reviewerService.validateAndGetReviewer(reviewerId);
        reviewerService.deleteReviewer(reviewer);
        return reviewerMapper.toReviewerResponse(reviewer);
    }

    // -------
    // Article

    @GetMapping("/articles/{articleId}")
    public ArticleResponse getArticle(@PathVariable String articleId) {
        Article article = articleService.validateAndGetArticle(articleId);
        return articleMapper.toArticleResponse(article);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/articles")
    public ArticleResponse createArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        Article article = articleMapper.toArticle(createArticleRequest);
        article = articleService.createArticle(article);
        return articleMapper.toArticleResponse(article);
    }

    @DeleteMapping("/articles/{articleId}")
    public ArticleResponse deleteArticle(@PathVariable String articleId) {
        Article article = articleService.validateAndGetArticle(articleId);
        articleService.deleteArticle(article);
        return articleMapper.toArticleResponse(article);
    }

    // --------
    // Comments

    @GetMapping("/comments/{commentId}")
    public CommentResponse getComment(@PathVariable String commentId) {
        Comment comment = commentService.validateAndGetComment(commentId);
        return commentMapper.toCommentResponse(comment);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comments")
    public CommentResponse createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest) {
        Comment comment = commentMapper.toComment(createCommentRequest);
        comment = commentService.saveComment(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public CommentResponse deleteComment(@PathVariable String commentId) {
        Comment comment = commentService.validateAndGetComment(commentId);
        commentService.deleteComment(comment);
        return commentMapper.toCommentResponse(comment);
    }
}
