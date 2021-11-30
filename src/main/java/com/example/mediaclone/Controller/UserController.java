package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping
    public String index() {
        return "sign_up";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute UserDetails userDetails, Model model) {
//        System.out.println("User from UI: " + userDetails.getFirst_name());
        UserDetails user = new UserDetails();
        user.setFirst_name(userDetails.getFirst_name());
        user.setLast_name(userDetails.getLast_name());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setGender(userDetails.getGender());
        user.setDate_of_birth(userDetails.getDate_of_birth());

        UserDetails user1 = userServiceImplementation.saveUser(user);
        System.out.println(user1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("user", userDetails);
        return modelAndView;
    }

}
