package ru.vlad.springApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Engine {

    private Long id;

    private String description;

    private BigDecimal price;

    private int power;

    public int getPower() {
        return power;
    }


}
