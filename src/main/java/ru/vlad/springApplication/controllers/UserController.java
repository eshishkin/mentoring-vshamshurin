package ru.vlad.springApplication.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.User;
import ru.vlad.springApplication.services.impl.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl serviceInterface;
    public UserController(UserServiceImpl serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @PostMapping(value = "/create")
    public void create(@RequestBody User user) {
        serviceInterface.create(user);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        final User user = serviceInterface.read(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public void update(@PathVariable(name = "id") long id, @RequestBody User user) {
        serviceInterface.update(user, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        serviceInterface.delete(id);
    }

    @GetMapping("/list")
    public ModelAndView read(Model model) {
        final List<User> users = serviceInterface.readAll();
        model.addAttribute("users", users);

        if (users != null)
            return new ModelAndView("user_list", HttpStatus.OK);
        else
            return new ModelAndView("redirect:/engine/list", HttpStatus.NOT_FOUND);
    }

}
