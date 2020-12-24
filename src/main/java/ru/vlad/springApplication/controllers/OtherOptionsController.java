package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return new ModelAndView("otheroption_create");
    }

    @PostMapping("/create")
    public ModelAndView otherOptionCreate(OtherOption otherOption) {
        otherOptionService.create(otherOption);
        return new ModelAndView("redirect:/otherOption/list");
    }

    @GetMapping("/list")
    public ModelAndView listOtherOptions() {
        ModelAndView modelAndView = new ModelAndView("otheroption_list", HttpStatus.OK);
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
        ModelAndView modelAndView = new ModelAndView("otheroption_edit");
        modelAndView.addObject("otherOption", otherOptionService.read(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView otherOptionUpdate(OtherOption otherOption) {
        otherOptionService.update(otherOption, otherOption.getId());
        return new ModelAndView("redirect:/otherOption/list");
    }

}
