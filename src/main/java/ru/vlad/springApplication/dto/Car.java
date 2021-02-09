package ru.vlad.springApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String brand;

    @NotNull
    private Long wheelsId;

    @NotNull
    private Long transmissionId;

    @NotNull
    private Long engineId;

    private List<Long> otherOptions;

}
