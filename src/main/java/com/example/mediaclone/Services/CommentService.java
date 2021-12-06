package com.example.mediaclone.Services;

import com.example.mediaclone.Models.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    List<Comment> getComments();
}
