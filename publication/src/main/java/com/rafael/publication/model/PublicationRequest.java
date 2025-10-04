package com.rafael.publication.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublicationRequest {
    
    @NotBlank(message = "Title is required")
    private String title;

    private String imageUrl;

    @NotBlank(message = "Text is required")
    private String text;

}
