package com.rafael.publication.service;

import java.util.List;

import com.rafael.publication.domain.Comment;

public interface RedisService {

    void save(List<Comment> comments, String id);

    List<Comment> findById(String id);
}
