package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addPost(@ModelAttribute Post post, HttpSession session, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post newPost = new Post();

        newPost.setUser(user);
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        postServiceImpl.addPost(newPost);

        System.out.println("Post of id: " + newPost.getId() + ". Created by " + user.getFirst_name() + " " + user.getLast_name());

        postServiceImpl.viewDashboard(model);
        return "/dashboard";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post post = postServiceImpl.getPostById(Long.parseLong(postId));

        // check if current user is owner of post
        boolean validCreator = post.getUser().equals(user);

        if(validCreator) {
            postServiceImpl.deletePost(post);
            System.out.println("Post was deleted by " + user.getFirst_name());
        }

        postServiceImpl.viewDashboard(model);
        return "dashboard";
    }

    @GetMapping("/showPostEditForm/{id}")
    public String showPostEditForm(@PathVariable (value = "id") Long id, Model model) {
        // get post from posts
        Post post = postServiceImpl.getPostById(id);

        // set post as model attribute to pre-populate the form
        model.addAttribute("post", post);


        return "post_edit_page";
    }

    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable String id, HttpSession session,
                             Model model, @RequestParam(value = "content") String content) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post post = postServiceImpl.getPostById(Long.parseLong(id));
        boolean validCreator = post.getUser().equals(user);

        if(validCreator) {
            String message = "Edited!";
            post.setContent(content);
            post.setEditNotice(message);
            postServiceImpl.addPost(post);
            System.out.println("Post was edited");
        }

        postServiceImpl.viewDashboard(model);
        return "dashboard";
    }



//    @PostMapping("/updatePost/{postId}")
//    public String updatePost(HttpSession session, @PathVariable String postId, @RequestParam(value = "content") String content) {
//        User user = (User)session.getAttribute("user");
//        Post thePost = postService.getPostById(Long.parseLong(postId));
//
//        thePost.setContent(content);
//        thePost.setUser(user);
//        postService.addPost(thePost);
//        return "redirect:/home";
//
//    }
}
