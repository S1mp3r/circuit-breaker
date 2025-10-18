package com.rafael.comment.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.comment.entities.CommentEntity;
import com.rafael.comment.models.Comment;
import com.rafael.comment.models.CommentRequest;
import com.rafael.comment.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private boolean isOn = true;
    
    private final CommentRepository repository;

    @Transactional
    public void insert(CommentRequest commentRequest) {
        if (isOn) {
            var comment = new CommentEntity();

            comment.setPublicationId(commentRequest.getPublicationId());
            comment.setAuthor(commentRequest.getAuthor());
            comment.setText(commentRequest.getText());
            comment.setTimestamp(LocalDate.now());

            repository.save(comment);
        } else {
            throw new RuntimeException("Service unavailable");
        }
    }

    public List<Comment> findAllByPublicationId(String publicationId) {
        if (isOn) {
            final List<CommentEntity> commentEntities = repository.findAllByPublicationId(publicationId);
            final List<Comment> comments = new ArrayList<>();
    
            commentEntities.forEach(comment -> {
                var com = new Comment();
                com.setId(comment.getId());
                com.setAuthor(comment.getAuthor());
                com.setText(comment.getText());
                com.setTimestamp(comment.getTimestamp());
                comments.add(com);
            });
    
            return comments;
        } else {
            throw new RuntimeException("Service unavailable");
        }
    }

    public void changeIsOn(CommentRequest commentRequest) {
        isOn = commentRequest.getIsOn();
    }

}
