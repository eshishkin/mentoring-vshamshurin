package ru.vlad.springApplication.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.repository.UserRepository;
import ru.vlad.springApplication.services.ServiceInterface;

@Service
public class UserServiceImpl implements ServiceInterface<ModelUser, Long> {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(ModelUser model) {
        userRepository.save(model);
    }

    @Override
    public List<ModelUser> readAll() {
        return userRepository.findAll();
    }

    @Override
    public ModelUser read(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean update(ModelUser model, Long id) {
        if (userRepository.existsById(id)) {
            model.setId(id);
            userRepository.save(model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
