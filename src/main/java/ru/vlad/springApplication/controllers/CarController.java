package ru.vlad.springApplication.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.services.impl.CarServiceImpl;
import ru.vlad.springApplication.services.impl.EngineServiceImpl;
import ru.vlad.springApplication.services.impl.OtherOptionServiceImpl;
import ru.vlad.springApplication.services.impl.TransmissionServiceImpl;
import ru.vlad.springApplication.services.impl.WheelsServiceImpl;

@Controller
@RequestMapping("/cars")
public class CarController {
    public static final String CARS = "car_create";
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
        modelAndView.addObject("wheelsModel", wheelsService.readAll());
        modelAndView.addObject("enginesModel", engineService.readAll());
        modelAndView.addObject("transmissionsModel", transmissionService.readAll());
        modelAndView.addObject("otherOptionModel", otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView createCar(Car car) {
        carService.create(car);
        return new ModelAndView("redirect:/cars", HttpStatus.CREATED);
    }

    @PostMapping("/price")
    public ModelAndView getPrice(Car car) {
        ModelAndView model = new ModelAndView("redirect:/cars");
        car.setPrice(carService.getPrice(car));
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView editCar(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("car_edit", HttpStatus.OK);
        modelAndView.addObject("car", carService.read(id));
        modelAndView.addObject("wheelsModel", wheelsService.readAll());
        modelAndView.addObject("enginesModel", engineService.readAll());
        modelAndView.addObject("transmissionsModel", transmissionService.readAll());
        modelAndView.addObject("otherOptionModel", otherOptionService.readAll());
        return modelAndView;
    }

    @PostMapping(value = "/update/{id}")
    public ModelAndView updateCar(@PathVariable(name = "id") long id, Car car) {
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
