package com.example.mediaclone.Services;

import com.example.mediaclone.Models.Post;

import java.util.List;

public interface PostService {
    void addPost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);

    List<Post> getPosts();
}
