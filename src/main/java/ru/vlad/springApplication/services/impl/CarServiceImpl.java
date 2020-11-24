package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.repository.CarRepository;
import ru.vlad.springApplication.services.ServiceInterface;

import java.util.List;

@Service
public class CarServiceImpl implements ServiceInterface<ModelCar, Long> {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void create(ModelCar model) {
        carRepository.save(model);
    }

    @Override
    public List<ModelCar> readAll() {
        return carRepository.findAll();
    }

    @Override
    public ModelCar read(Long id) {
        return carRepository.getOne(id);
    }

    @Override
    public boolean update(ModelCar car, Long id) {
       if (carRepository.existsById(id)) {
            car.setId(id);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
