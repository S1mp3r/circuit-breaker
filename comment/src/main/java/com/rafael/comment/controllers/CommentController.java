package com.rafael.comment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.comment.models.Comment;
import com.rafael.comment.models.CommentRequest;
import com.rafael.comment.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService service;

    @PostMapping
    @CrossOrigin
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postComment(@Valid @RequestBody CommentRequest commentRequest) {        
        service.insert(commentRequest);
    }
    
    @GetMapping("/{publicationId}")
    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> findAllByPublicationId(@PathVariable String publicationId) {
        return service.findAllByPublicationId(publicationId);
    }

    @PatchMapping
    @CrossOrigin
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void changeServiceStatus(@RequestBody CommentRequest commentRequest) {
        service.changeIsOn(commentRequest);
    }
}
