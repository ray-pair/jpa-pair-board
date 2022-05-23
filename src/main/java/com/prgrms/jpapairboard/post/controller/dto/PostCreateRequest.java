package com.prgrms.jpapairboard.post.controller.dto;

import com.prgrms.jpapairboard.post.entity.Post;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PostCreateRequest {
    @NotBlank
    @Length(max = 50)
    private final String title;
    @NotBlank
    private final String content;
    @Positive
    @NotNull
    private final Long userId;

    public PostCreateRequest(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Post toEntity() {
        return new Post(title, content);
    }
}
