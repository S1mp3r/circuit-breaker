package com.rafael.publication.service.impl;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rafael.publication.domain.Comment;
import com.rafael.publication.service.RedisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final String KEY = "comment";
    
    private final RedisTemplate<String, Object> redis;

    @Override
    public void save(List<Comment> comments, String id) {
        redis.opsForHash().put(KEY, id, comments);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> findById(String id) {
        return (List<Comment>) redis.opsForHash().get(KEY, id);
    }

}
