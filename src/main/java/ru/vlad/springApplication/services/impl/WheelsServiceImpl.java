package ru.vlad.springApplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Wheels;
import ru.vlad.springApplication.models.ModelWheels;
import ru.vlad.springApplication.repository.WheelsRepository;

@Service
public class WheelsServiceImpl {

    private final WheelsRepository wheelsRepository;

    public WheelsServiceImpl(WheelsRepository wheelsRepository) {
        this.wheelsRepository = wheelsRepository;
    }

    public void create(Wheels wheels) {
        wheelsRepository.save(createModelWheels(wheels));
    }

    public List<Wheels> readAll() {
        List<ModelWheels> modelWheelsList = wheelsRepository.findAll();
        List<Wheels> wheels = new ArrayList<>();
        for (ModelWheels wheels1 : modelWheelsList) {
            wheels.add(createDTOOWheels(wheels1));
        }
        return wheels;
    }

    public Wheels read(Long id) {
        return createDTOOWheels(wheelsRepository.getOne(id));
    }

    public boolean update(Wheels wheels, Long id) {
        if (wheelsRepository.existsById(id)) {
            wheels.setId(id);
            wheelsRepository.save(createModelWheels(wheels));
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
