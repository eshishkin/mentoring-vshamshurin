package ru.vlad.springApplication.services.impl;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(ModelUser model) {
        userRepository.save(model);
    }

    public List<ModelUser> readAll() {
        return userRepository.findAll();
    }

    public ModelUser read(Long id) {
        return userRepository.getOne(id);
    }

    public boolean update(ModelUser model, Long id) {
        if (userRepository.existsById(id)) {
            model.setId(id);
            userRepository.save(model);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
