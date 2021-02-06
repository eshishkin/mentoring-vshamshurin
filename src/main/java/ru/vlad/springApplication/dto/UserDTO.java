package ru.vlad.springApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Email
    @Size(max = 150)
    private String email;

    @NotNull
    @Size(max = 50)
    private String phone;

    @NotNull
    private Long id;

    @NotNull
    private String role;

    @NotNull
    @Size(max = 150)
    private String password;

    public UserDTO() {}
}
