package com.example.mediaclone.Services;

import com.example.mediaclone.Models.UserDetails;

import java.util.List;

public interface Service {
    List<UserDetails> findAllUsers();

    UserDetails saveUser(UserDetails userDetails);

    UserDetails authenticate(String login, String password);
}
