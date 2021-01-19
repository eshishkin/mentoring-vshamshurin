package ru.vlad.springApplication.services.impl;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.models.ModelOtherOption;
import ru.vlad.springApplication.repository.CarRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl {

    private final CarRepository carRepository;
    private final EngineServiceImpl engineService;
    private final TransmissionServiceImpl transmissionService;
    private final WheelsServiceImpl wheelsService;
    private final OtherOptionServiceImpl otherOptionService;

    public CarServiceImpl(CarRepository carRepository, EngineServiceImpl engineService,
                          TransmissionServiceImpl transmissionService, WheelsServiceImpl wheelsService,
                          OtherOptionServiceImpl otherOptionService) {
        this.carRepository = carRepository;
        this.engineService = engineService;
        this.transmissionService = transmissionService;
        this.wheelsService = wheelsService;
        this.otherOptionService = otherOptionService;
    }

    public void create(Car car) {
        carRepository.save(createModelCar(car));
    }

    public List<ModelCar> readAllModelCar() {
        return carRepository.findAll();
    }

    public List<Car> readAll() {
        List<ModelCar> modelCarList = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        for (ModelCar modelCar : modelCarList) {
            carList.add(createDTOCar(modelCar));
        }
        return carList;
    }

    public Car read(Long id) {
        return createDTOCar(carRepository.getOne(id));
    }

    public boolean update(Car car, Long id) {
       if (carRepository.existsById(id)) {
            car.setId(id);
            carRepository.save(createModelCar(car));
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BigDecimal getPrice(Car car) {
        ModelCar modelCar = createModelCar(car);
        BigDecimal price = new BigDecimal("0.0");
        price = price.add(modelCar.getEngine().getPrice()).add(modelCar.getTransmission().getPrice())
                .add(modelCar.getWheels().getPrice());
        for (ModelOtherOption modelOtherOption : modelCar.getOtherOption()) {
            price = price.add(modelOtherOption.getPrice());
        }
        return price;
    }

    public ModelCar createModelCar(@NotNull Car car) {
        ModelCar modelCar = new ModelCar();
        modelCar.setId(car.getId());
        modelCar.setBrand(car.getBrand());
        modelCar.setEngine(engineService.createModelEngine(engineService.read(car.getEngineId())));
        modelCar.setTransmission(transmissionService.createModelTransmission(
                transmissionService.read(car.getTransmissionId())));
        modelCar.setWheels(wheelsService.createModelWheels(wheelsService.read(car.getWheelsId())));
        List<ModelOtherOption> otherOption = new ArrayList<>();
        for (int i = 0; i < car.getOtherOptions().size(); i++) {
            otherOption.add(otherOptionService.createModelOtherOption(otherOptionService
                    .read(car.getOtherOptions().get(i))));
        }
        modelCar.setOtherOption(otherOption);
        return modelCar;
    }


    public Car createDTOCar(@NotNull ModelCar car) {
        Car carDTO = new Car();
        carDTO.setBrand(car.getBrand());
        carDTO.setId(car.getId());
        carDTO.setEngineId(engineService.createDTOEngine(car.getEngine()).getId());
        carDTO.setTransmissionId(transmissionService.createDTOTransmission(car.getTransmission()).getId());
        carDTO.setWheelsId(wheelsService.createDtoWheels(car.getWheels()).getId());
        List<Long> options = new ArrayList<>();
        for (ModelOtherOption otherOption : car.getOtherOption()) {
            options.add(otherOptionService.createDTOOtherOption(otherOption).getId());
        }
        carDTO.setOtherOptions(options);
        return carDTO;
    }



}
