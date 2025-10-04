package com.rafael.publication.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rafael.publication.domain.Comment;

@FeignClient(name = "CommentClient", url = "${comment.service.url}")
public interface CommentClient {

    @GetMapping("/comments/{publicationId}")
    List<Comment> findAllByPublicationId(@PathVariable String publicationId);

}
