package ru.vlad.springApplication.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class ModelCar implements Model<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
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

    @ManyToMany
    @JoinTable(
            name = "cars_other_options",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<ModelOtherOption> otherOption;

    public ModelCar() {}

    public void addOtherOption(ModelOtherOption modelOtherOption) {
        otherOption.add(modelOtherOption);
    }

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

    public List<ModelOtherOption> getOtherOption() {
        return otherOption;
    }

    public void setOtherOption(List<ModelOtherOption> otherOption) {
        this.otherOption = otherOption;
    }
}
