package com.rafael.publication.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publication {
    
    private String id;
    private String title;
    private String imageUrl;
    private String text;
    private String author;
    private List<Comment> comments;

}
