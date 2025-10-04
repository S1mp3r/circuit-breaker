package com.rafael.publication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafael.publication.entity.PublicationEntity;

@Repository
public interface PublicationRepository extends MongoRepository<PublicationEntity, String> {

    
}