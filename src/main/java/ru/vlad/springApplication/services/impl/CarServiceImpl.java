package ru.vlad.springApplication.services.impl;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.models.ModelOtherOption;
import ru.vlad.springApplication.repository.CarRepository;

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

    public void create(ModelCar model) {
        carRepository.save(model);
    }

    public List<ModelCar> readAll() {
        return carRepository.findAll();
    }

    public ModelCar read(Long id) {
        return carRepository.getOne(id);
    }

    public boolean update(ModelCar car, Long id) {
       if (carRepository.existsById(id)) {
            car.setId(id);
            carRepository.save(car);
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

    public void actionCreateModelCar(Car car) {
        create(createModelCar(car));
    }

    public void actionUpdateModelCar(Car car) {
        update(createModelCar(car), car.getId());
    }

    public ModelCar createModelCar(@NotNull Car car) {
        ModelCar modelCar = new ModelCar();
        modelCar.setBrand(car.getBrand());
        modelCar.setEngine(engineService.read(car.getEngine_id()));
        modelCar.setTransmission(transmissionService.read(car.getTransmission_id()));
        modelCar.setWheels(wheelsService.read(car.getWheels_id()));
        List<ModelOtherOption> otherOption = new ArrayList<>();
        for (int i = 0; i < car.getOtherOption_id().size(); i++) {
            otherOption.add(otherOptionService.read(car.getOtherOption_id().get(i)));
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
        carDTO.setOtherOption_id(options);
        return carDTO;
    }

}
