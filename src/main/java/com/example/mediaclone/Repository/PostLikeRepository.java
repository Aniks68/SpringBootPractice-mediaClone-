package com.example.mediaclone.Repository;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.PostLike;
import com.example.mediaclone.Models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findByPostAndUser(Long postId, Long userId);
}
