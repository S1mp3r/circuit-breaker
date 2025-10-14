package com.rafael.publication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafael.publication.entities.PublicationEntity;

@Repository
public interface PublicationRepository extends MongoRepository<PublicationEntity, String> {

    
}