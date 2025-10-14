package com.rafael.publication.services;

import java.util.List;

import com.rafael.publication.domains.Publication;

public interface PublicationService {

    public void insert(Publication publication);

    public List<Publication> findAll();

    public Publication findById(String id);

}