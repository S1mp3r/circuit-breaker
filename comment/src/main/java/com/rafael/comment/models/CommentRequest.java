package com.rafael.comment.models;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    
    private String id;

    @NotBlank
    private String publicationId;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Comment is required")
    private String text;
    
    private LocalDate timestamp;

    private Boolean isOn;

}
