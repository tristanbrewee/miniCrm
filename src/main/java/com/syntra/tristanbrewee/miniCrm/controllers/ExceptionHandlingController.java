package com.syntra.tristanbrewee.miniCrm.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController implements ErrorController {


    @RequestMapping("/error")
    public String getGeneralError(Model model, Exception exception){
        model.addAttribute("exception", exception);
        return "bootstrap/error/unexpectedError";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
