package com.example.mediaclone.Repository;

import com.example.mediaclone.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findPostById(Long id);
    Post findPostByTitle(String title);
}
