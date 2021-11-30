package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@org.springframework.stereotype.Controller
public class AuthController {
    private UserServiceImplementation userServiceImplementation;
    @Autowired
    public AuthController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
    @RequestMapping( "/")
    public String viewHomePage(Model model) {
        UserDetails user = new UserDetails();
        model.addAttribute("user", user);
        return "index";
    }
    @RequestMapping(value = "/homePage", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") UserDetails user, Model model) {
        String email = user.getEmail();
        String password = user.getPassword();
        Optional<UserDetails> optional =  userServiceImplementation.getUserByEmail(email);
        System.out.println(optional);
        if(optional.isPresent()){
            if(optional.get().getPassword().equals(password)){
                model.addAttribute("validDetails", true);
                model.addAttribute("first_name", optional.get().getFirst_name());
                model.addAttribute("last_name", optional.get().getLast_name());
                model.addAttribute("userid", optional.get().getId());
                model.addAttribute("yourmind", "Whats on your mind " + optional.get().getLast_name());
                return "home";
            }else{
                model.addAttribute("invalidPassword", true);
                return "login";
            }
        }
        model.addAttribute("invalidDetails", true);
        return "login";
    }
    @RequestMapping(value = "/signUpUser", method = RequestMethod.POST)
    public String signUpUser(@ModelAttribute("user") UserDetails user, Model model){
        model.addAttribute("user", user);
        userServiceImplementation.saveUser(user);
        return "login";
    }
//    @PostMapping("/saveUserDetails")
//    public String saveUserDetails(@ModelAttribute("user") UserInfo user) {
//        userServiceImplementation.saveUser(user);
//        return "redirect:/";
//    }
}