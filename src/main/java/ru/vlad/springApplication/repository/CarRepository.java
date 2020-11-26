package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelCar;

public interface CarRepository extends JpaRepository<ModelCar, Long> {

}
