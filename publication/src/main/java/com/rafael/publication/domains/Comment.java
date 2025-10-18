package com.rafael.publication.domains;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Comment {
    
    private String id;
    private String author;
    private String text;
    private LocalDate timestamp;

}
