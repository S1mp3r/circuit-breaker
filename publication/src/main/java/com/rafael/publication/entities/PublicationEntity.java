package com.rafael.publication.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document(collection = "publications")
public class PublicationEntity {
    
    @MongoId
    private String id;
    private String title;
    private String imageUrl;
    private String text;

}
