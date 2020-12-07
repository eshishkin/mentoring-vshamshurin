package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelEngine;

public interface EngineRepository extends JpaRepository<ModelEngine, Long> {
}
