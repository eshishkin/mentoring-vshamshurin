package ru.vlad.springApplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.OtherOption;
import ru.vlad.springApplication.models.ModelOtherOption;
import ru.vlad.springApplication.repository.OtherOptionRepository;

@Service
public class OtherOptionServiceImpl {

    private final OtherOptionRepository otherOptionRepository;

    public OtherOptionServiceImpl(OtherOptionRepository otherOptionRepository) {
        this.otherOptionRepository = otherOptionRepository;
    }

    public void create(OtherOption otherOption) {
        otherOptionRepository.save(createModelOtherOption(otherOption));
    }

    public List<OtherOption> readAll() {
        List<ModelOtherOption> modelOtherOptionList = otherOptionRepository.findAll();
        List<OtherOption> otherOptionList = new ArrayList<>();
        modelOtherOptionList.forEach(x -> otherOptionList.add(createDTOOtherOption(x)));
        return otherOptionList;
    }

    public OtherOption read(Long id) {
        return createDTOOtherOption(otherOptionRepository.getOne(id));
    }

    public boolean update(OtherOption otherOption, Long id) {
        if (otherOptionRepository.existsById(id)) {
            otherOption.setId(id);
            otherOptionRepository.save(createModelOtherOption(otherOption));
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
