package com.rafael.publication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.publication.domain.Publication;
import com.rafael.publication.mapper.PublicationMapper;
import com.rafael.publication.model.PublicationRequest;
import com.rafael.publication.service.PublicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationMapper mapper;
    private final PublicationService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insert(@Valid @RequestBody PublicationRequest publication) {
        service.insert(mapper.toPublication(publication));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Publication> findAll() {
        var publications = service.findAll();
        return publications;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Publication findById(@PathVariable("id") String id) {
        return service.findById(id);
    }
    
}