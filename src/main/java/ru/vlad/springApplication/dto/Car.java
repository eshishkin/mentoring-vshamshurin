package ru.vlad.springApplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Car {

    private Long id;

    private String brand;

    private Long wheels_id;

    private Long transmission_id;

    private Long engine_id;

    private List<Long> otherOption_id;

}
