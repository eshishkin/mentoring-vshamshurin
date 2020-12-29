package ru.vlad.springApplication.controllers;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.services.impl.CarServiceImpl;
import ru.vlad.springApplication.services.impl.EngineServiceImpl;
import ru.vlad.springApplication.services.impl.OtherOptionServiceImpl;
import ru.vlad.springApplication.services.impl.TransmissionServiceImpl;
import ru.vlad.springApplication.services.impl.WheelsServiceImpl;

@Controller
@RequestMapping("/cars")
public class CarController {
    public static final String CARS = "car_create";
    public static final String WHEELS_MODEL = "wheelsModel";
    public static final String ENGINES_MODEL = "enginesModel";
    public static final String TRANSMISSIONS_MODEL = "transmissionsModel";
    public static final String OTHER_OPTION_MODEL = "otherOptionModel";
    public static final String PRICE = "price";
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
    public ModelAndView createForm(@RequestParam(value = "price", required = false) BigDecimal price,
                                   @ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView(CARS, HttpStatus.OK);
        modelAndView.addObject(WHEELS_MODEL, wheelsService.readAll());
        modelAndView.addObject(ENGINES_MODEL, engineService.readAll());
        modelAndView.addObject(TRANSMISSIONS_MODEL, transmissionService.readAll());
        modelAndView.addObject(OTHER_OPTION_MODEL, otherOptionService.readAll());
        modelAndView.addObject(PRICE, price);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCar(Car car) {
        carService.create(car);
        return new ModelAndView("redirect:/cars", HttpStatus.CREATED);
    }

    @SuppressWarnings("MultipleStringLiterals")
    @PostMapping("/price")
    public ModelAndView getPrice(Car car) {
        ModelAndView model = new ModelAndView("redirect:/cars?price=" + carService.getPrice(car)
                + "&brand=" + car.getBrand() + "&wheels_id=" + car.getWheelsId()
                + "&transmission_id=" + car.getTransmissionId() + "&engine_id=" + car.getEngineId());
        model.addObject("car", car);
        return model;
    }

    @GetMapping("/update/{id}")
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
