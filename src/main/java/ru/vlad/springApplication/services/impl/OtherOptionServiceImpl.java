package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.OtherOption;
import ru.vlad.springApplication.models.ModelOtherOption;
import ru.vlad.springApplication.repository.OtherOptionRepository;

import java.util.List;

@Service
public class OtherOptionServiceImpl {

    private final OtherOptionRepository otherOptionRepository;

    public OtherOptionServiceImpl(OtherOptionRepository otherOptionRepository) {
        this.otherOptionRepository = otherOptionRepository;
    }

    public void create(ModelOtherOption model) {
        otherOptionRepository.save(model);
    }

    public List<ModelOtherOption> readAll() {
        return otherOptionRepository.findAll();
    }

    public ModelOtherOption read(Long id) {
        return otherOptionRepository.getOne(id);
    }

    public boolean update(ModelOtherOption model, Long id) {
        if (otherOptionRepository.existsById(id)) {
            model.setId(id);
            otherOptionRepository.save(model);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (otherOptionRepository.existsById(id)) {
            otherOptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ModelOtherOption createModelOtherOption(OtherOption otherOption) {
        return new ModelOtherOption(otherOption.getId(), otherOption.getName(), otherOption.getDescription(),
                otherOption.getPrice());
    }

    public OtherOption createDTOOtherOption(ModelOtherOption otherOption) {
        return new OtherOption(otherOption.getId(), otherOption.getName(), otherOption.getDescription(),
                otherOption.getPrice());
    }
}
