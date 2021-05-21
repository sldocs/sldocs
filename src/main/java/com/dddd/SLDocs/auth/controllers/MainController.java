package com.dddd.SLDocs.auth.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "500";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
