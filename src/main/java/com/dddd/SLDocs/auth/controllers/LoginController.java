package com.dddd.SLDocs.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }
    @GetMapping("/login?error")
    public String viewLoginErrorPage(@RequestParam String error) {

        return "login";
    }

}
