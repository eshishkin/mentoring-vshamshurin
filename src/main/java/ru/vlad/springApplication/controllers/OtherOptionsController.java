package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.OtherOption;
import ru.vlad.springApplication.services.impl.OtherOptionServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/otherOption")
public class OtherOptionsController {

    private static final String REDIRECT_TO_OPTION_LIST = "redirect:/otherOption/list";
    private final OtherOptionServiceImpl otherOptionService;

    public OtherOptionsController(OtherOptionServiceImpl otherOptionService) {
        this.otherOptionService = otherOptionService;
    }

    @GetMapping("/create")
    public ModelAndView otherOptionCreateView(@ModelAttribute("otherOption") OtherOption otherOption) {
        return new ModelAndView("otheroption_create");
    }

    @PostMapping("/create")
    public ModelAndView otherOptionCreate(@Valid OtherOption otherOption, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/validation/error");
        }
        otherOptionService.create(otherOption);
        return new ModelAndView(REDIRECT_TO_OPTION_LIST);
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
        return new ModelAndView(REDIRECT_TO_OPTION_LIST);
    }

    @GetMapping("/update/{id}")
    public ModelAndView otherOptionUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("otheroption_edit");
        modelAndView.addObject("otherOption", otherOptionService.read(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView otherOptionUpdate(@Valid OtherOption otherOption, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/validation/error");
        }
        otherOptionService.update(otherOption, otherOption.getId());
        return new ModelAndView(REDIRECT_TO_OPTION_LIST);
    }

}
