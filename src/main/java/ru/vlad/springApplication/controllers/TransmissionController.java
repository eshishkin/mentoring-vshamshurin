package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Transmission;
import ru.vlad.springApplication.services.impl.TransmissionServiceImpl;

@Controller
@RequestMapping("/transmission")
public class TransmissionController {

    private final TransmissionServiceImpl transmissionService;

    public TransmissionController(TransmissionServiceImpl transmissionService) {
        this.transmissionService = transmissionService;
    }

    @GetMapping("/create")
    public ModelAndView transmissionCreateView(@ModelAttribute("transmission") Transmission transmission) {
        return new ModelAndView("transmissionCreate");
    }

    @PostMapping("/create")
    public ModelAndView transmissionCreate(Transmission transmission) {
        transmissionService.create(transmissionService.createModelTransmission(transmission));
        return new ModelAndView("redirect:/transmission/list");
    }

    @GetMapping("/list")
    public ModelAndView listTransmissions() {
        ModelAndView modelAndView = new ModelAndView("transmissionsList", HttpStatus.OK);
        modelAndView.addObject("transmissions", transmissionService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView transmissionDelete(@PathVariable("id") long id) {
        transmissionService.delete(id);
        return new ModelAndView("redirect:/transmission/list");
    }

    @GetMapping("/update/{id}")
    public ModelAndView transmissionUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("editTransmission");
        modelAndView.addObject("transmission", transmissionService.createDTOTransmission(transmissionService.read(id)));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView transmissionUpdate(Transmission transmission) {
        transmissionService.update(transmissionService.createModelTransmission(transmission), transmission.getId());
        return new ModelAndView("redirect:/transmission/list");
    }
}
