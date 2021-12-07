package com.example.mediaclone.Services;

import com.example.mediaclone.Models.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    Comment getComment(Long id);
    void deleteComment(Comment comment);
    List<Comment> getComments();
}
