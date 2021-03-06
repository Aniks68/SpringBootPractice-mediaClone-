package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.PostLike;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.CommentServiceImpl;
import com.example.mediaclone.Services.ServiceImpl.PostLikeServiceImpl;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    private PostServiceImpl postServiceImpl;
    private CommentServiceImpl commentServiceImpl;
    private PostLikeServiceImpl postLikeServiceImpl;


    @Autowired
    public PostController(PostServiceImpl postServiceImpl, CommentServiceImpl commentServiceImpl, PostLikeServiceImpl postLikeServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.commentServiceImpl = commentServiceImpl;
        this.postLikeServiceImpl = postLikeServiceImpl;
    }

    @GetMapping("/post")
    public String getPostPage() {
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

        postServiceImpl.viewDashboard(model);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post post = postServiceImpl.getPostById(Long.parseLong(postId));

        // check if current user is owner of post
        boolean validCreator = post.getUser().equals(user);

        if(validCreator) {
            postServiceImpl.deletePost(post);
        }

        postServiceImpl.viewDashboard(model);
        return "redirect:/dashboard";
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

        // check if current user is also the post's owner
        boolean validCreator = post.getUser().equals(user);

        if(validCreator) {
            post.setContent(content);
            post.setEditNotice("Edited!");
            postServiceImpl.addPost(post);
            System.out.println("Post was edited");
        }

        postServiceImpl.viewDashboard(model);
        return "redirect:/dashboard";
    }

    @PostMapping("/like/{id}")
    public String likeIndex(@PathVariable("id") Long id, HttpSession session, PostLike like, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        if( user == null) return "redirect:/login";

        Post post = postServiceImpl.getPostById(id);
        PostLike postLike = postLikeServiceImpl.getPostLikeByPostAndUser(post, user);

        like.setPost(post);
        like.setUser(user);

        if(postLike == null) {
            postLikeServiceImpl.addLike(like);
        } else {
            postLikeServiceImpl.delete(postLike);
        }
        return "redirect:/dashboard";
    }
}
