package ru.vlad.springApplication.controllers;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.UserDTO;
import ru.vlad.springApplication.services.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl serviceInterface;

    public UserController(UserServiceImpl serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @GetMapping("/create")
    public ModelAndView createForm(@ModelAttribute("user") UserDTO user) {
        return new ModelAndView("user_create");
    }

    @PostMapping(value = "/create")
    public ModelAndView create(UserDTO user) {
        serviceInterface.create(user);
        return new ModelAndView("redirect:/users/list");
    }

    @GetMapping("/edit/{id}")
    @SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE", justification = "False positive?")
    public ModelAndView getUser(@PathVariable long id) {
        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("user", serviceInterface.read(id));
        return model;
    }

    @PostMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") long id, UserDTO user) {
        serviceInterface.update(user, id);
        return new ModelAndView("redirect:/users/list");
    }

    @PostMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        serviceInterface.delete(id);
        return new ModelAndView("redirect:/users/list");
    }

    @GetMapping("/list")
    @SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE", justification = "False positive?")
    public ModelAndView read(Model model) {
        final List<UserDTO> users = serviceInterface.readAll();
        model.addAttribute("users", users);
        if (users != null) {
            return new ModelAndView("user_list", HttpStatus.OK);
        } else {
            return new ModelAndView("redirect:/engine/list", HttpStatus.NOT_FOUND);
        }
    }

}
