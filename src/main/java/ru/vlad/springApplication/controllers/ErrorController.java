package ru.vlad.springApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/access-denied")
public class ErrorController {

    @GetMapping
    public ModelAndView controllerError() {
        return new ModelAndView("security_error");
    }
}
