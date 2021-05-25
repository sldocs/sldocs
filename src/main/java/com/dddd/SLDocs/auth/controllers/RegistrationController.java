package com.dddd.SLDocs.auth.controllers;

import com.dddd.SLDocs.auth.entities.User;
import com.dddd.SLDocs.auth.servImpls.UserDetailsServiceImpl;
import com.dddd.SLDocs.auth.servImpls.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/registration")
    public String viewRegPage(){
        return "registration";
    }
    @PostMapping(path = "/registration")
    public String createAccount(Model model, @RequestParam("password") String password,
                                @RequestParam("username") String email) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (userService.getUserByUsername(email)==null) {
            User user = new User();
            user.setUsername(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEnabled(true);
            userService.addUser(user);
        }else {
            String emailError="true";
            model.addAttribute(emailError, "emailError");
            return "registration";
        }
        return "redirect:/login";
    }
}

