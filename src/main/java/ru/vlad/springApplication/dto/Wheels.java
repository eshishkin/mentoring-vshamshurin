package ru.vlad.springApplication.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wheels {

    private Long id;

    private String description;

    private BigDecimal price;

    private int radius;

}
