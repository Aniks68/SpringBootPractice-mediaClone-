package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Comment;
import com.example.mediaclone.Repository.CommentRepository;
import com.example.mediaclone.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    final
    CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
