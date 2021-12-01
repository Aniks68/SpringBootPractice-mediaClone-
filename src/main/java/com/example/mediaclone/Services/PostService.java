package com.example.mediaclone.Services;

import com.example.mediaclone.Models.Post;

public interface PostService {
    public void addPost(Post post);

    public void deletePost(Post post);

    public Post getPostById(Long id);

    public Iterable<Post> getPosts();
}
