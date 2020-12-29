package ru.vlad.springApplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.Engine;
import ru.vlad.springApplication.models.ModelEngine;
import ru.vlad.springApplication.repository.EngineRepository;

@Service
public class EngineServiceImpl {

    protected final EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public void create(Engine model) {
        engineRepository.save(createModelEngine(model));
    }

    public List<Engine> readAll() {
        List<ModelEngine> modelEngineList = engineRepository.findAll();
        List<Engine> engineList = new ArrayList<>();
        for (ModelEngine modelEngine : modelEngineList) {
            engineList.add(createDTOEngine(modelEngine));
        }
        return engineList;
    }

    public Engine read(Long id) {
        return createDTOEngine(engineRepository.getOne(id));
    }

    public boolean update(Engine model, Long id) {
        if (engineRepository.existsById(id)) {
            model.setId(id);
            engineRepository.save(createModelEngine(model));
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
