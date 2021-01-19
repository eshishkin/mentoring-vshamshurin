package ru.vlad.springApplication.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wheels")
public class ModelWheels implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "radius")
    private int radius;

    public ModelWheels() {
    }

    public ModelWheels(Long id, String description, BigDecimal price, int radius) {
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
