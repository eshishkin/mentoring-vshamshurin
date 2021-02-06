package ru.vlad.springApplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.services.impl.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cars")
public class CarController {
    public static final String CARS = "car_create";
    public static final String WHEELS_MODEL = "wheelsModel";
    public static final String ENGINES_MODEL = "enginesModel";
    public static final String TRANSMISSIONS_MODEL = "transmissionsModel";
    public static final String OTHER_OPTION_MODEL = "otherOptionModel";
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
        ModelAndView modelAndView = new ModelAndView(CARS, HttpStatus.OK);
        modelAndView.addObject(WHEELS_MODEL, wheelsService.readAll());
        modelAndView.addObject(ENGINES_MODEL, engineService.readAll());
        modelAndView.addObject(TRANSMISSIONS_MODEL, transmissionService.readAll());
        modelAndView.addObject(OTHER_OPTION_MODEL, otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCar(@Valid Car car) {
        carService.create(car);
        return new ModelAndView("redirect:/cars", HttpStatus.CREATED);
    }

    @SuppressWarnings("MultipleStringLiterals")
    @PostMapping("/price")
    public String getPrice(Car car) {
        return "redirect:/cars/price?price=" + carService.getPrice(car);
    }

    @GetMapping("/price")
    public ModelAndView getPrice(@RequestParam("price") BigDecimal price) {
        ModelAndView model = new ModelAndView("car_price");
        model.addObject("price", price);
        return model;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCar(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("car_edit", HttpStatus.OK);
        modelAndView.addObject("car", carService.read(id));
        modelAndView.addObject(WHEELS_MODEL, wheelsService.readAll());
        modelAndView.addObject(ENGINES_MODEL, engineService.readAll());
        modelAndView.addObject(TRANSMISSIONS_MODEL, transmissionService.readAll());
        modelAndView.addObject(OTHER_OPTION_MODEL, otherOptionService.readAll());
        return modelAndView;
    }

    @SuppressWarnings(value = "MultipleStringLiterals")
    @PostMapping(value = "/update/{id}")
    public ModelAndView updateCar(@PathVariable(name = "id") long id, @Valid Car car) {
        carService.update(car, id);
        return new ModelAndView("redirect:/cars/list");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCar(@PathVariable(name = "id") long id) {
        carService.delete(id);
        return new ModelAndView("redirect:/cars/list");
    }

    @GetMapping("/list")
    public ModelAndView read() {
        ModelAndView model = new ModelAndView("car_list", HttpStatus.OK);
        model.addObject("cars", carService.readAllModelCar());
        return model;
    }
}
