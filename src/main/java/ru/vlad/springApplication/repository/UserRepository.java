package ru.vlad.springApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.springApplication.models.ModelUser;

public interface UserRepository extends JpaRepository<ModelUser, Long> {

}
