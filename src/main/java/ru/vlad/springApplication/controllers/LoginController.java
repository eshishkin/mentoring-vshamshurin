package ru.vlad.springApplication.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.UserDTO;
import ru.vlad.springApplication.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Controller
public class LoginController {
    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма входа");
        return "security_login";
    }

    @GetMapping("/register")
    public ModelAndView registerForm(@ModelAttribute("user") UserDTO user) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", new UserDTO());
        return new ModelAndView("security_register", model);
    }

    @PostMapping("/register")
    public ModelAndView register(UserDTO user) {
        userService.create(user);
        return new ModelAndView("redirect:/login");
    }
}
