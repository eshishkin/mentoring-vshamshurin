package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelTransmission;

public interface TransmissionRepository extends JpaRepository<ModelTransmission, Long> {
}
