package ru.vlad.springApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ModelCar implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "wheels_id")
    private long wheels_id;
    @Column(name = "transmission_id")
    private long transmission_id;
    @Column(name = "engine_id")
    private long engine_id;

    public ModelCar() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getWheels_id() {
        return wheels_id;
    }

    public void setWheels_id(long wheels_id) {
        this.wheels_id = wheels_id;
    }

    public long getTransmission_id() {
        return transmission_id;
    }

    public void setTransmission_id(long transmission_id) {
        this.transmission_id = transmission_id;
    }

    public long getEngine_id() {
        return engine_id;
    }

    public void setEngine_id(long engine_id) {
        this.engine_id = engine_id;
    }
}
