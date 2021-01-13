package ru.vlad.springApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private String brand;

    private Long wheelsId;

    private Long transmissionId;

    private Long engineId;

    private List<Long> otherOptions;

}
