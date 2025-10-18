package com.rafael.comment.entities;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "comments")
public class CommentEntity {
    
    @MongoId
    private String id;
    private String publicationId;
    private String author;
    private String text;
    private LocalDate timestamp;

}