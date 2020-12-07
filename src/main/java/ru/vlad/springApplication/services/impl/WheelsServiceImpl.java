package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Wheels;
import ru.vlad.springApplication.models.ModelWheels;
import ru.vlad.springApplication.repository.WheelsRepository;

import java.util.List;

@Service
public class WheelsServiceImpl {

    private final WheelsRepository wheelsRepository;

    public WheelsServiceImpl(WheelsRepository wheelsRepository) {
        this.wheelsRepository = wheelsRepository;
    }

    public void create(ModelWheels model) {
        wheelsRepository.save(model);
    }

    public List<ModelWheels> readAll() {
        return wheelsRepository.findAll();
    }

    public ModelWheels read(Long id) {
        return wheelsRepository.getOne(id);
    }

    public boolean update(ModelWheels wheel, Long id) {
        if (wheelsRepository.existsById(id)) {
            wheel.setId(id);
            wheelsRepository.save(wheel);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (wheelsRepository.existsById(id)) {
            wheelsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ModelWheels createModelWheels(Wheels wheels) {
        return new ModelWheels(wheels.getId(), wheels.getDescription(), wheels.getPrice(), wheels.getRadius());
    }


    public Wheels createDTOOWheels(ModelWheels modelWheels) {
        return new Wheels(modelWheels.getId(), modelWheels.getDescription(), modelWheels.getPrice(),
                modelWheels.getRadius());
    }
}
