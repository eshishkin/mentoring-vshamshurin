package ru.vlad.springApplication.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private String brand;

    private Long wheels_id;

    private Long transmission_id;

    private Long engine_id;

    private List<Long> otherOptions;

    private BigDecimal price = new BigDecimal("0.00");


}
