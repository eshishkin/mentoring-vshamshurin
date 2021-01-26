package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Engine;
import ru.vlad.springApplication.services.impl.EngineServiceImpl;

@Controller
@RequestMapping("/engine")
public class EngineController {

    public static final String REDIRECT_ENGINE_LIST = "redirect:/engine/list";
    private final EngineServiceImpl engineService;

    public EngineController(EngineServiceImpl engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/create")
    public ModelAndView engineCreateView(@ModelAttribute("engine") Engine engine) {
        return new ModelAndView("engine_create", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ModelAndView engineCreate(Engine engine) {
        engineService.create(engine);
        return new ModelAndView(REDIRECT_ENGINE_LIST);
    }

    @GetMapping("/list")
    public ModelAndView listEngines() {
        ModelAndView modelAndView = new ModelAndView("engine_list", HttpStatus.OK);
        modelAndView.addObject("engines", engineService.readAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView engineDelete(@PathVariable("id") long id) {
        engineService.delete(id);
        return new ModelAndView(REDIRECT_ENGINE_LIST);
    }

    @GetMapping("/update/{id}")
    public ModelAndView engineUpdateView(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("engine_edit");
        modelAndView.addObject("engine", engineService.read(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView engineUpdate(Engine engine) {
        engineService.update(engine, engine.getId());
        return new ModelAndView(REDIRECT_ENGINE_LIST);
    }
}
