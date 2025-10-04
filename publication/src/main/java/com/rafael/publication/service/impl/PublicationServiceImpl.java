package com.rafael.publication.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.publication.domain.Publication;
import com.rafael.publication.mapper.PublicationMapper;
import com.rafael.publication.repository.PublicationRepository;
import com.rafael.publication.service.CommentService;
import com.rafael.publication.service.PublicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    
    private final PublicationRepository repository;
    private final PublicationMapper mapper;
    private final CommentService commentService;

    @Override
    @Transactional
    public void insert(Publication publication) {
        repository.save(mapper.toPublicationEntity(publication));
    }

    @Override
    public List<Publication> findAll() {
        var publications = repository.findAll();
        return publications.stream().map(mapper::toPublication).toList();
    }

    @Override
    public Publication findById(String id) {
        var publication = repository.findById(id)
                .map(mapper::toPublication)
                .orElseThrow(() -> new RuntimeException("Publication not found"))
        ;
        
        var comments = commentService.findAllByPublicationId(id);
        publication.setComments(comments);

        return publication;
    }
}
