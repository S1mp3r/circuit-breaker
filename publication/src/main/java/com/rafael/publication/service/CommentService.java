package com.rafael.publication.service;

import java.util.List;

import com.rafael.publication.domain.Comment;

public interface CommentService {
    
    List<Comment> findAllByPublicationId(String publicationId);

}
