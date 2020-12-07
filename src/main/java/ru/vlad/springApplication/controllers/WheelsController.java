package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Wheels;
import ru.vlad.springApplication.services.impl.WheelsServiceImpl;

@Controller
@RequestMapping("/wheels")
public class WheelsController {

    private final WheelsServiceImpl wheelsService;

    public WheelsController(WheelsServiceImpl wheelsService) {
        this.wheelsService = wheelsService;
    }

    @GetMapping("/create")
    public ModelAndView wheelsCreateView(@ModelAttribute("wheels") Wheels wheels) {
        return new ModelAndView("wheelsCreate");
    }

    @PostMapping("/create")
    public ModelAndView wheelsCreate(Wheels wheels) {
        wheelsService.create(wheelsService.createModelWheels(wheels));
        return new ModelAndView("redirect:/wheels/list");
    }

    @GetMapping("/list")
    public ModelAndView listWheels() {
        ModelAndView modelAndView = new ModelAndView("wheelsList", HttpStatus.OK);
        modelAndView.addObject("wheelsList", wheelsService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView wheelsDelete(@PathVariable("id") long id) {
        wheelsService.delete(id);
        return new ModelAndView("redirect:/wheels/list");
    }

    @GetMapping("/update/{id}")
    public ModelAndView wheelsUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("editWheels");
        modelAndView.addObject("wheels", wheelsService.createDTOOWheels(wheelsService.read(id)));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView wheelsOptionUpdate(Wheels wheels) {
        wheelsService.update(wheelsService.createModelWheels(wheels), wheels.getId());
        return new ModelAndView("redirect:/wheels/list");
    }
}
