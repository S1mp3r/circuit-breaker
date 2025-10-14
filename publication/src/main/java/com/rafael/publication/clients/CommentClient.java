package com.rafael.publication.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rafael.publication.domains.Comment;

@FeignClient(name = "CommentClient", url = "${comment.service.url}")
public interface CommentClient {

    @GetMapping("/{publicationId}")
    List<Comment> findAllByPublicationId(@PathVariable String publicationId);

}
