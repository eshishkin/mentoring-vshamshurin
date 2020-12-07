package ru.vlad.springApplication.dto;

import ru.vlad.springApplication.models.ModelCar;

import java.math.BigDecimal;
import java.util.Set;

public class OtherOption {

    private long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Set<ModelCar> carsSet;

    public OtherOption() {}

    public OtherOption(long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCarsSet(Set<ModelCar> carsSet) {
        this.carsSet = carsSet;
    }

}
