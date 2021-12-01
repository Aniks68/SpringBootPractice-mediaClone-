package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Repository.PostRepository;
import com.example.mediaclone.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    final
    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}