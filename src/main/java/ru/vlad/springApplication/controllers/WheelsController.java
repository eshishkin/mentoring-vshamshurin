package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Wheels;
import ru.vlad.springApplication.services.impl.WheelsServiceImpl;

@Controller
@RequestMapping("/wheels")
public class WheelsController {

    private static final String REDIRECT_TO_WHEEL_LIST = "redirect:/wheels/list";
    private final WheelsServiceImpl wheelsService;

    public WheelsController(WheelsServiceImpl wheelsService) {
        this.wheelsService = wheelsService;
    }

    @GetMapping("/create")
    public ModelAndView wheelsCreateView(@ModelAttribute("wheels") Wheels wheels) {
        return new ModelAndView("wheels_create");
    }

    @PostMapping("/create")
    public ModelAndView wheelsCreate(Wheels wheels) {
        wheelsService.create(wheels);
        return new ModelAndView(REDIRECT_TO_WHEEL_LIST);
    }

    @GetMapping("/list")
    public ModelAndView listWheels() {
        ModelAndView modelAndView = new ModelAndView("wheels_list", HttpStatus.OK);
        modelAndView.addObject("wheelsList", wheelsService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView wheelsDelete(@PathVariable("id") long id) {
        wheelsService.delete(id);
        return new ModelAndView(REDIRECT_TO_WHEEL_LIST);
    }

    @GetMapping("/update/{id}")
    public ModelAndView wheelsUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("wheels_edit");
        modelAndView.addObject("wheels", wheelsService.read(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView wheelsOptionUpdate(Wheels wheels) {
        wheelsService.update(wheels, wheels.getId());
        return new ModelAndView(REDIRECT_TO_WHEEL_LIST);
    }
}
