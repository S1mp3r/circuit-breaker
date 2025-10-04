package com.rafael.publication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErroResponse {

    private Long timeStamp;
    private Integer status;
    private String message;
    private String path;

}