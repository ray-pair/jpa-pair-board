package com.prgrms.jpapairboard.post.controller;

import com.prgrms.jpapairboard.post.controller.dto.PostCreateRequest;
import com.prgrms.jpapairboard.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/posts")
@RestController
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        postService.save(postCreateRequest.getUserId(), postCreateRequest.toEntity());
    }
}
