package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.CommentServiceImpl;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    private PostServiceImpl postServiceImpl;
    private CommentServiceImpl commentServiceImpl;

    public CommentController(PostServiceImpl postServiceImpl, CommentServiceImpl commentServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/showCommentForm/{id}")
    public String showCommentEditForm(@PathVariable(value = "id") Long id, Model model) {
        // get post from posts
        Post post = postServiceImpl.getPostById(id);

        // set post as model attribute to pre-populate the form
        model.addAttribute("post", post);

        return "comment_page";
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable String id, @ModelAttribute Comment comment, HttpSession session, Model model) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        Post post = postServiceImpl.getPostById(Long.parseLong(id));
        Comment newComment = new Comment();

        newComment.setContent(comment.getContent());
        newComment.setPost(post);
        newComment.setUser(user);
        commentServiceImpl.addComment(newComment);
        System.out.println("Comment of id: " + newComment.getId() + ". Commented by " + newComment.getUser().getFirst_name()
                + " " + newComment.getUser().getLast_name() + ". For post by: " +newComment.getPost().getUser().getFirst_name());

        postServiceImpl.viewDashboard(model);
        return "dashboard";
    }
}
