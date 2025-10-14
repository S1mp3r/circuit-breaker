package com.rafael.publication.services;

import java.util.List;

import com.rafael.publication.domains.Comment;

public interface RedisService {

    void save(List<Comment> comments, String id);

    List<Comment> findById(String id);
}
