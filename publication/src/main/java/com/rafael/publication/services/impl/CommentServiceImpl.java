package com.rafael.publication.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.publication.clients.CommentClient;
import com.rafael.publication.domains.Comment;
import com.rafael.publication.services.CommentService;
import com.rafael.publication.services.RedisService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    
    private final CommentClient commentClient;
    private final RedisService redisService;

    @Override
    @CircuitBreaker(name = "comments", fallbackMethod = "findAllByFallback")
    public List<Comment> findAllByPublicationId(String publicationId) {

        var comments = commentClient.findAllByPublicationId(publicationId);
        redisService.save(comments, publicationId);

        return comments;
    }

    public List<Comment> findAllByFallback(String publicationId, Throwable throwable) {
        log.warn("[WARN] fallback in comments with id - {}", publicationId);
        return redisService.findById(publicationId);
    }
}
