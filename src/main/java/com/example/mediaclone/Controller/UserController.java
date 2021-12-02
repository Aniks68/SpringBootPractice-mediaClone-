package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserServiceImpl userServiceImplementation;

    @Autowired
    public UserController(UserServiceImpl userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
//        model.addAttribute("registerRequest", new UserDetails());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
//        model.addAttribute("loginRequest", new UserDetails());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDetails userDetails, Model model) {
        UserDetails user = new UserDetails();
        user.setFirst_name(userDetails.getFirst_name());
        user.setLast_name(userDetails.getLast_name());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setGender(userDetails.getGender());
        user.setDate_of_birth(userDetails.getDate_of_birth());

        System.out.println("Register request: " + userDetails);
        UserDetails registeredUser = userServiceImplementation.saveUser(user);
        System.out.println(registeredUser);
        String message = "Duplicate email registration attempt. ";
        model.addAttribute("errorMessage", message);

        return registeredUser == null ? "error_page" : "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDetails userDetails, Model model) {
        System.out.println("Login request: " + userDetails);
        UserDetails authenticated = userServiceImplementation.authenticate(userDetails.getEmail(), userDetails.getPassword());
        if(authenticated != null) {
            System.out.println("LoggedIn User: " + authenticated);
            model.addAttribute("userLogin", authenticated.getFirst_name() + " " + authenticated.getLast_name());
            return "home_page";
        } else {
            String message = "Incorrect login details. Wrong email or password. ";
            model.addAttribute("errorMessage", message);
            return "error_page";
        }
    }
}
