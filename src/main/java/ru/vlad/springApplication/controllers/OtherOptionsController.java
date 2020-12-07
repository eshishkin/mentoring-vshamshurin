package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.OtherOption;
import ru.vlad.springApplication.services.impl.OtherOptionServiceImpl;

@Controller
@RequestMapping("/otherOption")
public class OtherOptionsController {

    private final OtherOptionServiceImpl otherOptionService;

    public OtherOptionsController(OtherOptionServiceImpl otherOptionService) {
        this.otherOptionService = otherOptionService;
    }

    @GetMapping("/create")
    public ModelAndView otherOptionCreateView(@ModelAttribute("otherOption") OtherOption otherOption) {
        return new ModelAndView("otherOptionCreate");
    }

    @PostMapping("/create")
    public ModelAndView otherOptionCreate(OtherOption otherOption) {
        otherOptionService.create(otherOptionService.createModelOtherOption(otherOption));
        return new ModelAndView("redirect:/otherOption/list");
    }

    @GetMapping("/list")
    public ModelAndView listOtherOptions() {
        ModelAndView modelAndView = new ModelAndView("otherOptionsList", HttpStatus.OK);
        modelAndView.addObject("otherOptions", otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView otherOptionDelete(@PathVariable("id") long id) {
        otherOptionService.delete(id);
        return new ModelAndView("redirect:/otherOption/list");
    }

    @GetMapping("/update/{id}")
    public ModelAndView otherOptionUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("editOtherOption");
        modelAndView.addObject("otherOption", otherOptionService.createDTOOtherOption(otherOptionService.read(id)));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView otherOptionUpdate(OtherOption otherOption) {
        otherOptionService.update(otherOptionService.createModelOtherOption(otherOption), otherOption.getId());
        return new ModelAndView("redirect:/otherOption/list");
    }

}
