package ru.vlad.springApplication.dto;

import java.math.BigDecimal;

public class Wheels {

    private Long id;

    private String description;

    private BigDecimal price;

    public Wheels() {}

    private int radius;



    public Wheels(Long id, String description, BigDecimal price, int radius) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
