package com.example.mediaclone.Repository;

import com.example.mediaclone.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
}
