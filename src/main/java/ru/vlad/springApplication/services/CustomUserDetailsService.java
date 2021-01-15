package ru.vlad.springApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.models.ModelUser;
import ru.vlad.springApplication.repository.UserRepository;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ModelUser myUser = userRepository.findByName(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }

        return User.builder()
                .username(myUser.getName())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
    }
}
