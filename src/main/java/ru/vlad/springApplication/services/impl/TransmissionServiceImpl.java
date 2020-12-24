package ru.vlad.springApplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Car;
import ru.vlad.springApplication.dto.Transmission;
import ru.vlad.springApplication.models.ModelCar;
import ru.vlad.springApplication.models.ModelTransmission;
import ru.vlad.springApplication.repository.TransmissionRepository;

@Service
public class TransmissionServiceImpl {

    private final TransmissionRepository transmissionRepository;

    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public void create(Transmission transmission) {
        transmissionRepository.save(createModelTransmission(transmission));
    }

    public List<Transmission> readAll() {
        List<ModelTransmission> modelTransmissionList = transmissionRepository.findAll();
        List<Transmission> transmissionList = new ArrayList<>();
        for (ModelTransmission modelTransmission : modelTransmissionList) {
            transmissionList.add(createDTOTransmission(modelTransmission));
        }
        return transmissionList;
    }

    public Transmission read(Long id) {
        return createDTOTransmission(transmissionRepository.getOne(id));
    }

    public boolean update(Transmission transmission, Long id) {
        if (transmissionRepository.existsById(id)) {
            transmission.setId(id);
            transmissionRepository.save(createModelTransmission(transmission));
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (transmissionRepository.existsById(id)) {
            transmissionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ModelTransmission createModelTransmission(Transmission transmission) {
        return new ModelTransmission(transmission.getId(), transmission.getDescription(), transmission.getPrice());
    }

    public Transmission createDTOTransmission(ModelTransmission transmission) {
        return new Transmission(transmission.getId(), transmission.getDescription(), transmission.getPrice());
    }
}
