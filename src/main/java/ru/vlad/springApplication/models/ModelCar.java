package ru.vlad.springApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ModelCar implements Model<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @OneToOne(optional = false)
    @JoinColumn(name = "wheels_id", nullable = false)
    private ModelWheels wheels;

    @OneToOne(optional = false)
    @JoinColumn(name = "transmission_id", nullable = false)
    private ModelTransmission transmission;

    @OneToOne(optional = false)
    @JoinColumn(name = "engine_id", nullable = false)
    private ModelEngine engine;

    public ModelCar() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ModelWheels getWheels() {
        return wheels;
    }

    public void setWheels(ModelWheels wheels) {
        this.wheels = wheels;
    }

    public ModelTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(ModelTransmission transmission) {
        this.transmission = transmission;
    }

    public ModelEngine getEngine() {
        return engine;
    }

    public void setEngine(ModelEngine engine) {
        this.engine = engine;
    }
}
