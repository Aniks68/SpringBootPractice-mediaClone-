package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    private PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @GetMapping("/post")
    public String getPostPage(HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        System.out.println("Session user is " + user.getFirst_name() + " " + user.getLast_name());

        return "post_page";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute Post post, HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post newPost = new Post();

        newPost.setUser(user);
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        postServiceImpl.addPost(newPost);
        System.out.println("Post of id: " + newPost.getId() + ". Created by " + user.getFirst_name() + " " + user.getLast_name());
        return "/dashboard";
    }


}
