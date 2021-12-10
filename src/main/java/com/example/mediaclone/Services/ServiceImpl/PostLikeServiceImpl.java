package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.PostLike;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Repository.PostLikeRepository;
import com.example.mediaclone.Repository.PostRepository;
import com.example.mediaclone.Services.PostLikeService;
import org.springframework.stereotype.Service;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private PostLikeRepository likeRepository;
    private PostRepository postRepository;

    public PostLikeServiceImpl(PostLikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Override
    public PostLike getPostLikeByPostAndUser(Post post, UserDetails user) {
        return likeRepository.getPostLikeByPostAndUser(post, user);
    }

    @Override
    public void addLike(PostLike like) {
        likeRepository.save(like);
    }

    @Override
    public void delete(PostLike postLike) {
        likeRepository.delete(postLike);
    }
}
