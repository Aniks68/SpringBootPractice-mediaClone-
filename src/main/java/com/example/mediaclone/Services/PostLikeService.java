package com.example.mediaclone.Services;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.PostLike;
import com.example.mediaclone.Models.UserDetails;

public interface PostLikeService {
    PostLike getPostLikeByPostAndUser(Post post, UserDetails user);

    void addLike(PostLike like);

    void delete(PostLike postLike);
}
