package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelOtherOption;

public interface OtherOptionRepository extends JpaRepository<ModelOtherOption, Long> {
}
