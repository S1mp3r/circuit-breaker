package com.rafael.publication.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.publication.domains.Comment;
import com.rafael.publication.services.RedisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final String KEY = "comment";

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redis;

    @Override
    public void save(List<Comment> comments, String id) {
        try {
            String json = objectMapper.writeValueAsString(comments);
            redis.opsForHash().put(KEY, id, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize comments for Redis", e);
        }
    }

    @Override
    public List<Comment> findById(String id) {
        Object value = redis.opsForHash().get(KEY, id);
        if (value == null) return Collections.emptyList();

        try {
            return objectMapper.readValue(
                    value.toString(),
                    new TypeReference<List<Comment>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize comments from Redis", e);
        }
    }
}
