package ru.vlad.springApplication.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Car {

    private Long id;

    private String brand;

    private Long wheels_id;

    private Long transmission_id;

    private Long engine_id;

    private List<Long> otherOptions;


}
