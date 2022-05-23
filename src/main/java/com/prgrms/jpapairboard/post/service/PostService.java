package com.prgrms.jpapairboard.post.service;

import com.prgrms.jpapairboard.post.entity.Post;

public interface PostService {

    void save(Long userId, Post post);
}
