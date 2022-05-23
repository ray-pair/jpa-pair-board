package com.prgrms.jpapairboard.post.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Title should not be null")
    @Length(max = 50, message = "The maximum length of the title is 50")
    String title;

    @NotNull(message = "Content should not be null")
    @Lob
    String content;

    protected Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private Post(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
    }

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String content;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
