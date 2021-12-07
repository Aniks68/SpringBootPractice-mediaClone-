package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Repository.PostRepository;
import com.example.mediaclone.Services.PostService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final CommentServiceImpl commentService;

    public PostServiceImpl(PostRepository postRepository, CommentServiceImpl commentService) {
        this.postRepository = postRepository;
        this.commentService = commentService;
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
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public void viewDashboard(Model model){
        Post post = new Post();
        Comment comment = new Comment();
        List<Post> posts = this.getPosts();
        Collections.reverse(posts);

        List<Comment> comments = commentService.getComments();
        /* add posts to the model */
        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", comment);
    }
}

