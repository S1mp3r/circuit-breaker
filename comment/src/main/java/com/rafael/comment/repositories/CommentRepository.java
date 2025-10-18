package com.rafael.comment.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafael.comment.entities.CommentEntity;
import java.util.List;


@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    
    public List<CommentEntity> findAllByPublicationId(String publicationId);

}
