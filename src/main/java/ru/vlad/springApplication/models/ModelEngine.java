package ru.vlad.springApplication.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "engines")
public class ModelEngine implements Model<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "power")
    private int power;


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public ModelEngine() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
