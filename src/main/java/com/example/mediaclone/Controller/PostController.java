package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

//        Post post = postServiceImpl.getPostById(13L);
//        Post post2 = postServiceImpl.getPostById(14L);
//        postServiceImpl.deletePost(post);
//        postServiceImpl.deletePost(post2);

        return "post_page";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute Post post, HttpSession session, Model model, Model model2) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post newPost = new Post();

        newPost.setUser(user);
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        postServiceImpl.addPost(newPost);

        model.addAttribute("postUser", newPost.getUser().getFirst_name() + " " + newPost.getUser().getLast_name());
        model.addAttribute("postTitle", newPost.getTitle());
        model.addAttribute("postContent", newPost.getContent());
        System.out.println("Post of id: " + newPost.getId() + ". Created by " + user.getFirst_name() + " " + user.getLast_name());

        postServiceImpl.viewDashboard(model2);
        return "/dashboard";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post post = postServiceImpl.getPostById(Long.parseLong(postId));
        boolean validCreator = post.getUser().equals(user);

        if(validCreator) {
            postServiceImpl.deletePost(post);
            System.out.println("Post was deleted by " + user.getFirst_name());
        } else {
            String message = "You can't delete this post!";
            model.addAttribute("errorMessage", message);
        }
        return validCreator == true ? "/dashboard" : "/error_page";
    }
}
