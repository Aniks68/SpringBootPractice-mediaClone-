package com.example.mediaclone.Services.ServiceImpl;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import javax.persistence.Column;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails.setFirst_name("Prosper");
        userDetails.setLast_name("Amalaha");
        userDetails.setEmail("amalaha@gmail.com");
        userDetails.setPassword("amalahahaha");
        userDetails.setDate_of_birth("10/10/1995");
        userDetails.setGender("Male");

    }

    @Test
    @DisplayName("To test if method returns list of users")
    void findAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userDetails));  // pass in the mock list to the parenthesis and test assertions

        List<UserDetails> allUser = userService.findAllUsers();

        System.out.println(allUser);

       Assertions.assertEquals("[UserDetails(id=null, first_name=Prosper, " +
               "last_name=Amalaha, email=amalaha@gmail.com, password=amalahahaha, " +
               "date_of_birth=10/10/1995, gender=Male)]", Arrays.toString(allUser.toArray()));


    }

    @Test
    @DisplayName("To check if user details are saved")
    void saveUser() {
        when(userRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        userService.saveUser(userDetails);

        verify(userRepository, times(1)).save(any(UserDetails.class));
    }

    @Test
    @DisplayName("To check if user email and password combination exists")
    void authenticate() {
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(userDetails));
        UserDetails userInf = userService.authenticate(userDetails.getEmail(), userDetails.getPassword());

        Assertions.assertEquals("amalaha@gmail.com", userInf.getEmail());
        Assertions.assertEquals("amalahahaha", userInf.getPassword());

    }
}