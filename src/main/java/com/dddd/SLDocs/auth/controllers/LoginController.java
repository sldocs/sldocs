package com.dddd.SLDocs.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/login?error")
    public String viewLoginErrorPage(@RequestParam String error) {

        return "login";
    }

}
