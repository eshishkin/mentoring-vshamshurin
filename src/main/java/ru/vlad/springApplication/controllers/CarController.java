package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.services.impl.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarServiceImpl carService;
    private final WheelsServiceImpl wheelsService;
    private final EngineServiceImpl engineService;
    private final TransmissionServiceImpl transmissionService;
    private final OtherOptionServiceImpl otherOptionService;

    public CarController(CarServiceImpl carService, WheelsServiceImpl wheelsService,
                         EngineServiceImpl engineService, TransmissionServiceImpl transmissionService,
                         OtherOptionServiceImpl otherOptionService) {
        this.carService = carService;
        this.wheelsService = wheelsService;
        this.engineService = engineService;
        this.transmissionService = transmissionService;
        this.otherOptionService = otherOptionService;
    }

    @GetMapping
    public ModelAndView createForm(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView("cars", HttpStatus.OK);
        modelAndView.addObject("wheelsModel", wheelsService.readAll());
        modelAndView.addObject("enginesModel", engineService.readAll());
        modelAndView.addObject("transmissionsModel", transmissionService.readAll());
        modelAndView.addObject("otherOptionModel", otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView createCar(@ModelAttribute("car") Car car) {
        carService.actionCreateModelCar(car);
        return new ModelAndView("redirect:/cars", HttpStatus.CREATED);
    }

    @GetMapping("/update/{id}")
    public ModelAndView editCar(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("carEdit", HttpStatus.OK);
        modelAndView.addObject("car", carService.createDTOCar(carService.read(id)));
        modelAndView.addObject("wheelsModel", wheelsService.readAll());
        modelAndView.addObject("enginesModel", engineService.readAll());
        modelAndView.addObject("transmissionsModel", transmissionService.readAll());
        modelAndView.addObject("otherOptionModel", otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping(value = "/update/{id}")
    public ModelAndView updateCar(@PathVariable(name = "id") long id,@ModelAttribute("car") Car car) {
        carService.actionUpdateModelCar(car);
        return new ModelAndView("redirect:/cars/list", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCar(@PathVariable(name = "id") long id) {
        final boolean deleted = carService.delete(id);
        return new ModelAndView("redirect:/cars/list", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView read(Model model) {
        final List<ModelCar> cars = carService.readAll();
        model.addAttribute("cars", cars);
        return new ModelAndView("carsList");
    }
}
