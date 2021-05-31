package com.dddd.SLDocs.auth.controllers;

import com.dddd.SLDocs.auth.entities.User;
import com.dddd.SLDocs.auth.servImpls.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String viewLoginPage(Model model) {

        model.addAttribute("users", userService.listAll());
        return "users";
    }

    @RequestMapping(path = "/user/activate", method = RequestMethod.POST)
    public String enableUser(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        user.setEnabled(true);
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(path = "/user/deactivate", method = RequestMethod.POST)
    public String disableUser(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        user.setEnabled(false);
        userService.save(user);
        return "redirect:/users";
    }

}
