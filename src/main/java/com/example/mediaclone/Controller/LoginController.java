package com.example.mediaclone.Controller;

import com.example.mediaclone.Services.ServiceImpl.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login.html")
public class LoginController {
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public LoginController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping
    public String index() {
        return "login";
    }
}
