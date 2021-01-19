package ru.vlad.springApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String name;

    private String email;

    private String phone;

    private Long id;

    private String role;

    private String password;

    public UserDTO() {}
}
