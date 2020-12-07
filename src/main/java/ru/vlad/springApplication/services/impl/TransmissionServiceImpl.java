package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Transmission;
import ru.vlad.springApplication.models.ModelTransmission;
import ru.vlad.springApplication.repository.TransmissionRepository;

import java.util.List;

@Service
public class TransmissionServiceImpl {

    private final TransmissionRepository transmissionRepository;

    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public void create(ModelTransmission model) {
        transmissionRepository.save(model);
    }

    public List<ModelTransmission> readAll() {
        return transmissionRepository.findAll();
    }

    public ModelTransmission read(Long id) {
        return transmissionRepository.getOne(id);
    }

    public boolean update(ModelTransmission model, Long id) {
        if (transmissionRepository.existsById(id)) {
            model.setId(id);
            transmissionRepository.save(model);
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
