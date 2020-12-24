package ru.vlad.springApplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.User;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.repository.UserRepository;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.save(createModelUser(user));
    }

    public List<User> readAll() {
        List<ModelUser> modelUserList = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        modelUserList.forEach(x -> userList.add(createDTOUser(x)));
        return userList;
    }

    public User read(Long id) {
        return createDTOUser(userRepository.getOne(id));
    }

    public boolean update(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(createModelUser(user));
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

    public ModelUser createModelUser(User user) {
        return new ModelUser(user.getName(), user.getEmail(), user.getPhone(), user.getId());
    }

    public User createDTOUser(ModelUser modelUser) {
        return new User(modelUser.getName(), modelUser.getEmail(), modelUser.getPhone(), modelUser.getId());
    }
}
