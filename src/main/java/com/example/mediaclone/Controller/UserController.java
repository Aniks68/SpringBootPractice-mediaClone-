package com.example.mediaclone.Controller;

import com.example.mediaclone.Models.UserDetails;
import com.example.mediaclone.Services.ServiceImpl.PostServiceImpl;
import com.example.mediaclone.Services.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController implements ErrorController {
    private UserServiceImpl userServiceImplementation;
    private PostServiceImpl postServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImplementation, PostServiceImpl postServiceImpl) {
        this.userServiceImplementation = userServiceImplementation;
        this.postServiceImpl = postServiceImpl;
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
        user.setFirst_name(userDetails.formatString(userDetails.getFirst_name()));
        user.setLast_name(userDetails.formatString(userDetails.getLast_name()));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setGender(userDetails.getGender());
        user.setDate_of_birth(userDetails.getDate_of_birth());

        System.out.println("Register request: " + userDetails);


        UserDetails registeredUser = userServiceImplementation.saveUser(user);
        System.out.println("Registered: " + registeredUser);
        String message = "Duplicate email registration attempt. ";
        model.addAttribute("errorMessage", message);
        model.addAttribute("errorNotice", "RETURN TO REGISTRATION PAGE");
        model.addAttribute("errorLink", "/register");

        return registeredUser == null ? "error" : "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDetails userDetails, Model model, HttpSession session, Model model2) {
        System.out.println("Login request: " + userDetails);
        UserDetails authenticated = userServiceImplementation.authenticate(userDetails.getEmail(), userDetails.getPassword());
        if(authenticated != null) {
            System.out.println("LoggedIn User: " + authenticated);
            session.setAttribute("user", authenticated);
            model.addAttribute("userLogin", authenticated.getFirst_name() + " " + authenticated.getLast_name());

            postServiceImpl.viewDashboard(model2);
            return "redirect:/dashboard";
        } else {
            String message = "Incorrect login details. Wrong email or password. ";
            model.addAttribute("errorMessage", message);
            model.addAttribute("errorNotice", "RETURN TO LOGIN PAGE");
            model.addAttribute("errorLink", "/login");
            return "error";
        }
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model, HttpSession session, Model model1) {
        boolean validSession = session.getAttribute("user") == null;
        postServiceImpl.viewDashboard(model);
        model1.addAttribute("errorMessage", "Invalid session.");
        model1.addAttribute("errorNotice", "RETURN TO LOGIN PAGE");
        model1.addAttribute("errorLink", "/login");

        return validSession ? "error" : "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login_page";
    }

    @RequestMapping("/error")
    public String getDefaultError(Model model) {
        String message = "You have entered a wrong URL";
        model.addAttribute("errorMessage", message);
        model.addAttribute("errorNotice", "RETURN TO DASHBOARD PAGE");
        model.addAttribute("errorLink", "/dashboard");
        return "error";
    }
}
