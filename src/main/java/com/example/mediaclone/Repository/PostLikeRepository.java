package com.example.mediaclone.Repository;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.PostLike;
import com.example.mediaclone.Models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
//    List<PostLike> findAllByPostAndUser(Long postId, Long userId);
//    List<PostLike> findAllByPost(Long postId);

    @Transactional
    void deletePostLikeByPostAndUser(Post post, UserDetails user);

    PostLike getPostLikeByPostAndUser(Post post, UserDetails user);
}
