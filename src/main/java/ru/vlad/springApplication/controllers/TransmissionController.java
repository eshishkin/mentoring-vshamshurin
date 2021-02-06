package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Transmission;
import ru.vlad.springApplication.services.impl.TransmissionServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/transmission")
public class TransmissionController {

    public static final String REDIRECT_TRANSMISSION_LIST = "redirect:/transmission/list";
    private final TransmissionServiceImpl transmissionService;

    public TransmissionController(TransmissionServiceImpl transmissionService) {
        this.transmissionService = transmissionService;
    }

    @GetMapping("/create")
    public ModelAndView transmissionCreateView(@ModelAttribute("transmission") Transmission transmission) {
        return new ModelAndView("transmission_create");
    }

    @PostMapping("/create")
    public ModelAndView transmissionCreate(@Valid Transmission transmission) {
        transmissionService.create(transmission);
        return new ModelAndView(REDIRECT_TRANSMISSION_LIST);
    }

    @GetMapping("/list")
    public ModelAndView listTransmissions() {
        ModelAndView modelAndView = new ModelAndView("transmission_list", HttpStatus.OK);
        modelAndView.addObject("transmissions", transmissionService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView transmissionDelete(@PathVariable("id") long id) {
        transmissionService.delete(id);
        return new ModelAndView(REDIRECT_TRANSMISSION_LIST);
    }

    @GetMapping("/update/{id}")
    public ModelAndView transmissionUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("transmission_edit");
        modelAndView.addObject("transmission", transmissionService.read(id));
        return modelAndView;
    }



    @PostMapping("/update/{id}")
    public ModelAndView transmissionUpdate(@Valid Transmission transmission) {
        transmissionService.update(transmission, transmission.getId());
        return new ModelAndView(REDIRECT_TRANSMISSION_LIST);
    }
}
