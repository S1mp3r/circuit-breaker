package com.rafael.publication.services;

import java.util.List;

import com.rafael.publication.domains.Comment;

public interface CommentService {
    
    List<Comment> findAllByPublicationId(String publicationId);

}
