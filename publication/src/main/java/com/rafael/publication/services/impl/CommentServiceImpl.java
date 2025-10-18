package com.rafael.publication.services.impl;

import java.util.Collections;
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

        try {
            redisService.save(comments, publicationId);
        } catch (Exception e) {
            log.warn("[WARN] Failed to save comments in Redis for {}: {}", publicationId, e.getMessage());
        }

        return comments;
    }

    public List<Comment> findAllByFallback(String publicationId, Throwable throwable) {
        log.warn("[WARN] fallback in comments with id - {}", publicationId);
        
        try {
            return redisService.findById(publicationId);
        } catch (Exception ex) {
            log.error("[ERROR] Redis unavailable, returning empty comments for id {}", publicationId, ex);
            return Collections.emptyList();
        }
    }
}
