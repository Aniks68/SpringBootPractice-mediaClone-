package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Repository.UserRepository;
import com.example.mediaclone.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class UserServiceImplementation implements Service {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDetails> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails saveUser(UserDetails userDetails) {
        if(userRepository.findFirstByEmail(userDetails.getEmail()).isPresent()) {
            System.out.println("Duplicate email registration");
            return null;
        }
        return userRepository.save(userDetails);
    }

    @Override
    public UserDetails authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElse(null);
    }

}
