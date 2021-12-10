package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.Post;
import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Repository.PostRepository;
import com.example.mediaclone.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.annotation.CreatedDate;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    Post post;
    UserDetails user;


    @BeforeEach
    void setUp() {
        post = new Post();

        post.setTitle("Greetings");
        post.setContent("Hello World");

        user = new UserDetails();
        user.setFirst_name("Prosper");
        user.setLast_name("Amalaha");
        user.setEmail("amalaha@gmail.com");
        user.setPassword("amalahahaha");
        user.setDate_of_birth("10/10/1995");
        user.setGender("Male");


    }

    @Test
    @DisplayName("To test if a new post is created")
    void addPost() {

        when(postRepository.save(any(Post.class))).thenReturn(post);

        postServiceImpl.addPost(post);

        verify(postRepository, times(1)).save(any(Post.class));

    }

    @Test
    @DisplayName("To check if post gets deleted")
    void deletePost() {
        postRepository.delete(post);

        verify(postRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("To test if getting post by id returns an entity")
    void getPostById() {
        when(postRepository.findPostById(anyLong())).thenReturn(post);

        postServiceImpl.getPostById(1L);

        verify(postRepository, times(1)).findPostById(anyLong());
    }

}