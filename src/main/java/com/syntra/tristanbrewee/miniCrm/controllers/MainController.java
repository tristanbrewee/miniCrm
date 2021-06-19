package com.syntra.tristanbrewee.miniCrm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "bootstrap/main/home";
    }
}
