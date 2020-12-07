package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelWheels;

public interface WheelsRepository extends JpaRepository<ModelWheels, Long> {
}
