package ru.vlad.springApplication.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.dto.UserDTO;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.models.Role;
import ru.vlad.springApplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserDTO user) {
        userRepository.save(createModelUser(user));
    }

    public List<UserDTO> readAll() {
        List<ModelUser> modelUserList = userRepository.findAll();
        List<UserDTO> userList = new ArrayList<>();
        modelUserList.forEach(x -> userList.add(createDTOUser(x)));
        return userList;
    }

    public UserDTO read(Long id) {
        return createDTOUser(userRepository.getOne(id));
    }

    public boolean update(UserDTO user, Long id) {
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

    public ModelUser createModelUser(UserDTO user) {
        return new ModelUser(user.getName(), user.getEmail(), user.getPhone(), user.getId(), Role.valueOf(user.getRole()),
                passwordEncoder.encode(user.getPassword()));
    }

    public UserDTO createDTOUser(ModelUser modelUser) {
        return new UserDTO(modelUser.getName(), modelUser.getEmail(), modelUser.getPhone(), modelUser.getId(),
                modelUser.getRole(), modelUser.getPassword());
    }
}
