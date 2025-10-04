package com.rafael.publication.domain;

import java.util.List;

import lombok.Data;

@Data
public class Publication {
    
    private String title;
    private String imageURL;
    private String text;
    private List<Comment> comments;

}
