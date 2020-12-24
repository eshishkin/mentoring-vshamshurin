package ru.vlad.springApplication.services.impl;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.dto.OtherOption;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.models.ModelOtherOption;
import ru.vlad.springApplication.repository.CarRepository;

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

    public ModelCar createModelCar(@NotNull Car car) {
        ModelCar modelCar = new ModelCar();
        modelCar.setId(car.getId());
        modelCar.setBrand(car.getBrand());
        modelCar.setEngine(engineService.createModelEngine(engineService.read(car.getEngine_id())));
        modelCar.setTransmission(transmissionService.createModelTransmission(
                transmissionService.read(car.getTransmission_id())));
        modelCar.setWheels(wheelsService.createModelWheels(wheelsService.read(car.getWheels_id())));
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
        carDTO.setEngine_id(car.getEngine().getId());
        carDTO.setTransmission_id(car.getTransmission().getId());
        carDTO.setWheels_id(car.getWheels().getId());
        List<Long> options = new ArrayList<>();
        for (ModelOtherOption otherOption : car.getOtherOption()) {
            options.add(otherOption.getId());
        }
        carDTO.setOtherOptions(options);
        return carDTO;
    }



}
