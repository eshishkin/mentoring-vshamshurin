package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Engine;
import ru.vlad.springApplication.models.ModelEngine;
import ru.vlad.springApplication.repository.EngineRepository;
import java.util.List;

@Service
public class EngineServiceImpl {

    private final EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public void create(ModelEngine model) {
        engineRepository.save(model);
    }

    public List<ModelEngine> readAll() {
        return engineRepository.findAll();
    }

    public ModelEngine read(Long id) {
        return engineRepository.getOne(id);
    }

    public boolean update(ModelEngine model, Long id) {
        if (engineRepository.existsById(id)) {
            model.setId(id);
            engineRepository.save(model);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (engineRepository.existsById(id)) {
            engineRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ModelEngine createModelEngine(Engine engine) {
        ModelEngine modelEngine = new ModelEngine();
        modelEngine.setId(engine.getId());
        modelEngine.setDescription(engine.getDescription());
        modelEngine.setPrice(engine.getPrice());
        modelEngine.setPower(engine.getPower());
        return modelEngine;
    }

    public Engine createDTOEngine(ModelEngine modelEngine) {
        Engine engine = new Engine();
        engine.setId(modelEngine.getId());
        engine.setDescription(modelEngine.getDescription());
        engine.setPower(modelEngine.getPower());
        engine.setPrice(modelEngine.getPrice());
        return engine;
    }
}
