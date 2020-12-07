package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.services.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl serviceInterface;

    public UserController(UserServiceImpl serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody ModelUser user) {
        serviceInterface.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ModelUser> getUser(@PathVariable("id") long id) {
        final ModelUser user = serviceInterface.read(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody ModelUser user) {
        final boolean updated = serviceInterface.update(user, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = serviceInterface.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/list")
    public String read(Model model) {
        final List<ModelUser> users = serviceInterface.readAll();
        model.addAttribute("users", users);
        return "usersList";
    }


}
