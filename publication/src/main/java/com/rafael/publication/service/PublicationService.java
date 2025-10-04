package com.rafael.publication.service;

import java.util.List;

import com.rafael.publication.domain.Publication;

public interface PublicationService {

    public void insert(Publication publication);

    public List<Publication> findAll();

    public Publication findById(String id);

}