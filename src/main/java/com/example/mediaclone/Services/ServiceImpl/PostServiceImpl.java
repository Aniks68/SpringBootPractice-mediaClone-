package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Repository.PostRepository;
import com.example.mediaclone.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.function.Predicate;

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

    public void viewDashboard(Model model){
        Post post = new Post();
        Comment comment = new Comment();
        Iterable<Post> posts = this.getPosts();
        /* add posts to the model */
        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("comment", comment);
    }
//
//    private void extracted(Iterable<Post> posts) {
//        posts.forEach(x-> recentPosts().add(x));
//    }
//
//    public static Queue<Post> recentPosts() {
//        return postPriorityQueue;
//    }
//
//    private static Queue<Post> postPriorityQueue = new PriorityQueue<>(((o1, o2) -> {
//        final int post1Priority = Math.toIntExact(o1.getId());
//        final int post2Priority = Math.toIntExact(o2.getId());
//
//        return Integer.compare(post1Priority, post2Priority);
//    }));
}

