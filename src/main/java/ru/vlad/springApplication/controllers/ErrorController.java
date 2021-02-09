package ru.vlad.springApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public ModelAndView controllerError() {
        return new ModelAndView("security_error");
    }

    @GetMapping("/validation/error")
    public ModelAndView validationError() {
        return new ModelAndView("validation_error");
    }
}
